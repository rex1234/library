<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<s:layout-render name="/layout.jsp" titlekey="Ceil Library">    
    <s:layout-component name="tab">
        <li class="active"><a href="${pageContext.request.contextPath}/index.jsp"><span>Welcome</span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span>Impressions</span></s:link></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span>Readers</span></s:link></li>      
        </s:layout-component>
        <s:layout-component name="body">
        <h1>Welcome in our library</h1>
        <div class="post">
            <p>
                The library of IBTS, one of the largest English language theological libraries on the continent of Europe, is a rich resource for theological study. The library holds more than 70.000 volumes, including materials in German and other European languages, and subscribes to about 180 mainly theological periodicals.
                The Library is vital to theological education at IBTS. Students in various programmes learn to undertake independent research to receive the greatest benefit from their studies. Beside focussing on the needs of IBTS, the Library also serves the diverse EBF family and the wider scholarly community in Prague and beyond.
                The IBTS library is a highly respected academic resource provider within the Czech Republic and Europe. Two recent prestigious awards of book collections have further promoted the international standing of the library: it was selected as one of the twenty most important missiological libraries worldwide and was given the prestigious ISSR Award by the International Society for Science and Religion.
            </p>
        </div>          
    </s:layout-component>
</s:layout-render>