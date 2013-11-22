<%-- 
    Document   : impression_list
    Created on : Nov 22, 2013, 5:58:54 PM
    Author     : Mi?koHu
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="post">
    <table>
        <tr>
            <th scope="col"><f:message key="impression.isbn"/></th>
            <th scope="col"><f:message key="impression.author"/></th>
            <th scope="col"><f:message key="impression.name"/></th>
            <th scope="col"><f:message key="impression.releaseDate"/></th>
            <th scope="col"><f:message key="impression.department"/></th>            
        </tr>
        <c:forEach items="${actionBean.availableImpressions}" var="impression">
            <tr>
                <td><c:out value="${impression.isbn}"/></td>                    
                <td><c:out value="${impression.author}"/></td>
                <td><c:out value="${impression.name}"/></td>
                <td><j:format value="${impression.releaseDate}" pattern="dd.MM.yyyy"/></td>
                <td><f:message key="Department.${impression.department}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>