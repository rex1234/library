<%-- 
    Document   : book_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table border="1" style="border-collapse: collapse;">
    <tr>
        <th>Condition</th>
    </tr>
    <c:forEach items="${actionBean.impressionBooks}" var="book">
        <tr>
            <td><c:out value="${book.condition}"/></td>                    
            <td><s:link beanclass="cz.muni.fi.pa165.web.BookEditBean" event="editBook">
                    <s:param name="book.id" value="${book.id}"/><f:message key="edit"/></s:link></td>
            <td><s:link beanclass="cz.muni.fi.pa165.web.BookEditBean" event="deleteBook">
                    <s:param name="book.id" value="${book.id}"/><f:message key="delete"/></s:link></td>             
            </tr>
    </c:forEach>
</table>