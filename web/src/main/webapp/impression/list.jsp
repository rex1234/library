<%-- 
    Document   : impression_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table>
    <tr>
        <td valign="top">
            <table border="1" style="border-collapse: collapse;">
                <tr>
                    <th><f:message key="impression.isbn"/></th>
                    <th><f:message key="impression.author"/></th>
                    <th><f:message key="impression.name"/></th>
                    <th><f:message key="impression.releaseDate"/></th>
                    <th><f:message key="impression.department"/></th>
                    <th>Add book</th>
                </tr>
                <c:forEach items="${actionBean.allImpressions}" var="impression">
                    <tr>
                        <td><c:out value="${impression.isbn}"/></td>                    
                        <td><c:out value="${impression.author}"/></td>
                        <td><c:out value="${impression.name}"/></td>
                        <td><j:format value="${impression.releaseDate}" pattern="dd.MM.yyyy"/></td>
                        <td><f:message key="Department.${impression.department}"/></td>
                        <td><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean" event="edit">
                                <s:param name="impression.id" value="${impression.id}"/><f:message key="edit"/></s:link></td>
                        <td><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean" event="delete">
                                <s:param name="impression.id" value="${impression.id}"/><f:message key="delete"/></s:link></td>             
                        <td><s:link beanclass="cz.muni.fi.pa165.web.BookEditBean" event="createNewBook">
                                <s:param name="impression.id" value="${impression.id}"/>Add book</s:link></td>             
                        <td><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean" event="listBooks">
                                <s:param name="impression.id" value="${impression.id}"/>List books</s:link></td>   
                        </tr>
                </c:forEach>
            </table>
        </td>
        <td valign="top">
            <%@include file="../book/list.jsp"%> 
        </td>
    </tr>
</table>
