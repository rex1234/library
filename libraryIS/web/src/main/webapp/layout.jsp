<%-- 
    Document   : layout
    Created on : Nov 22, 2013, 3:31:08 PM
    Author     : MiškoHu
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css" />
            <s:layout-component name="header"/>
        </head>
        <body>
            <div id="wrap">
                <div id="top">
                    <h1 id="sitename">Library of Cejl</h1>                    
                </div>
                <div id="menu">
                    <ul>
                        <s:layout-component name="tab"/>                                       
                    </ul>     
                </div>
                <div id="contentwrap">
                    <div id="header"> </div>
                    <div id="mainpage">
                        <s:layout-component name="body"/> 
                    </div>
                </div>
            </div>       
        </body>
    </html>
</s:layout-definition>