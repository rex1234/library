<%-- 
    Document   : create
    Created on : Nov 22, 2013, 5:49:20 PM
    Author     : MiškoHu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<s:layout-render name="/layout.jsp" titlekey="Impressions">    
    <s:layout-component name="tab">
        <li><a href="${pageContext.request.contextPath}/index.jsp"><span>Welcome</span></a></li>
        <li><s:link beanclass="cz.muni.fi.pa165.web.ImpressionEditBean"><span>Impressions</span></s:link></li>
        <li class="active"><s:link beanclass="cz.muni.fi.pa165.web.CustomerEditBean"><span>Readers</span></s:link></li>      
    </s:layout-component>
    <s:layout-component name="body">
    <h3><f:message key="loan.new"/></h3>
    <h1><c:out value="${actionBean.vajce}"/></h1>
            <%@include file="impression_list.jsp"%> 
        <br/>
        <s:errors/>        
    </s:layout-component>
</s:layout-render>