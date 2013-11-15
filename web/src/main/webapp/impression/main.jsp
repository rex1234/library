<%-- 
    Document   : impression_edit
    Created on : 10.11.2013, 20:00:38
    Author     : Mjartan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add new impression</title>
    </head>
    <body> 
    <center>        
        <%@include file="list.jsp"%> 
        <br/>
        <s:errors/>
        <s:form beanclass="cz.muni.fi.pa165.web.ImpressionEditBean">
            <s:hidden name="impression.id"/>
            <fieldset>
                <%@include file="form.jsp"%>
            </fieldset>
            <s:submit name="create"><f:message key="insert"/></s:submit>
        </s:form>
    </center>
</body>
</html>
