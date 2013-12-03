<%-- 
    Document   : book_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<s:layout-render name="/layout.jsp" titlekey="books.title">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span>Welcome</span></a></li>
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span>Impressions</span></s:link></li>
        <li ><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span>Readers</span></s:link></li>      
        </s:layout-component>
        <s:layout-component name="body">
        <div class="post">
            <h2><f:message key="book.books"/>
                <c:out value="${actionBean.impression.name}"/></h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Condition</th>
                </tr>
                <c:forEach items="${actionBean.impressionBooks}" var="book">
                    <tr>
                        <td><s:link beanclass="cz.muni.fi.pa165.web.LoanEditBean" event="findByBook">
                                <s:param name="book.id" value="${book.id}"/><c:out value="${book.id}"/></s:link></td>                   
                        <td><c:out value="${book.condition}"/></td>                    
                        <td><s:link beanclass="cz.muni.fi.pa165.web.BookEditBean" event="editBook">
                                <s:param name="book.id" value="${book.id}"/><f:message key="edit"/></s:link></td>
                        <td><s:link beanclass="cz.muni.fi.pa165.web.BookEditBean" event="deleteBook">
                                <s:param name="book.id" value="${book.id}"/><f:message key="delete"/></s:link></td>             
                        </tr>
                </c:forEach>
            </table>
        </div>
        <h2><f:message key="book.newBook"/></h2>
        <s:errors/>
        <s:form beanclass="cz.muni.fi.pa165.web.BookEditBean">
            <s:hidden name="book.id"/>
            <s:hidden name="impression.id"/>
            <%@include file="form.jsp"%>
            <s:submit name="create"><f:message key="insert"/></s:submit>
        </s:form>
    </s:layout-component>
</s:layout-render>