<%-- 
    Document   : edit
    Created on : 12.11.2013, 18:43:26
    Author     : Rex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit impression</title>
    </head>
    <body> 
    <center>
        <s:errors/>
        <s:form beanclass="cz.muni.fi.pa165.web.ImpressionEditBean">
            <s:hidden name="impression.id"/>
            <%@include file="form.jsp"%>
            <s:submit name="save"><f:message key="save"/></s:submit>
        </s:form>
    </center>
</body>
</html>
