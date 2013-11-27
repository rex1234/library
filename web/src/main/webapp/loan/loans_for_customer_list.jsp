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
            <th scope="col"><f:message key="impression.name"/></th>
            <th scope="col"><f:message key="impression.author"/></th>
            <th scope="col"><f:message key="loan.from"/></th>
            <th scope="col"><f:message key="loan.to"/></th>            
            <th scope="col"><f:message key="loan.condition"/></th>      
        </tr>
        <c:forEach items="${actionBean.loans}" var="loan">
            <tr>
                <td><c:out value="${loan.book.impression.name}"/></td>                    
                <td><c:out value="${loan.book.impression.author}"/></td>               
                <td><j:format value="${loan.fromDate}" pattern="dd.MM.yyyy"/></td>
                <td><j:format value="${loan.toDate}" pattern="dd.MM.yyyy"/></td>
                <td><c:out value="${loan.conditionReturned}"/></td>                                     
                </tr>
        </c:forEach>
        </tbody>
    </table>
</div>