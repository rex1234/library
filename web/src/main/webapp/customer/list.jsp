<%-- 
    Document   : list
    Created on : Nov 21, 2013, 4:41:59 PM
    Author     : Matej
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %> 
<%@ taglib prefix="j" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="post">
    <table>
        <tr>
            <td valign="top">
                <table border="1" style="border-collapse: collapse;">
                    <tr>
                        <th><f:message key="customer.name"/></th>
                        <th><f:message key="customer.address"/></th>                                 
                    </tr>
                    <c:forEach items="${actionBean.allCustomers}" var="customer">
                        <tr>
                            <td><c:out value="${customer.name}"/></td>                    
                            <td><c:out value="${customer.address}"/></td>                                          
                        </tr>
                    </c:forEach>
                </table>
            </td>        
        </tr>
    </table>
</div>
