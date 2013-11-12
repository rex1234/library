package cz.muni.fi.pa165.web;

import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Department;
import cz.muni.fi.pa165.library.services.ImpressionService;
import java.util.Arrays;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.joda.time.LocalDate;

/**
 *
 * @author Mjartan
 */
public class ImpressionEditBean extends BaseBean {
    @SpringBean 
    private ImpressionService imService;
    
    @ValidateNestedProperties(value = {
        @Validate(on={"create"}, field="isbn", required = true),
        @Validate(on={"create"}, field="name", required = true),
    })  
    private ImpressionTO impression;            
    
    @DefaultHandler
    public Resolution defaultHandler() {
        return new ForwardResolution("/impression_edit.jsp");
    }
    
    public Resolution create() {    
        impression.setRelaseDate(new LocalDate(1990,1,1));
        imService.createImpression(impression);
        System.out.println(Arrays.toString(imService.findAllImpressions().toArray()));
        return new ForwardResolution("/index.jsp");
    }

    public ImpressionTO getImpression() {
        return impression;
    }    
    
    public void setImpression(ImpressionTO impression) {
        this.impression = impression;
    }        
}
