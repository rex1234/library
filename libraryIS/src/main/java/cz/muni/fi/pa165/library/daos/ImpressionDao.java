package cz.muni.fi.pa165.library.daos;

import cz.muni.fi.pa165.library.entities.Impression;
import java.util.List;

/**
 *
 * @author Mjartan
 */
public interface ImpressionDao {
    void createImpression(Impression book);
    List<Impression> findAllImpressions();
    Impression findBookById(Long id);
    void deleteImpression(Impression impression);
    void updateImpression(Impression impression);    
}
