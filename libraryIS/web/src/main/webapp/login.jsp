<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="login.title">       
    <s:layout-component name="body"> 

        <h1><f:message key="main.title" /></h1>

        <form name='f' action="<c:url value='j_spring_security_check' />" method='POST'>
            <c:if test="${param.badpw == '1'}">
            Bad login<br/>
            </c:if>
            <label for="j_username">Username</label>
            <input id="j_username" name="j_username" type="text" value="" autofocus /><br/>
            <label for="j_password">Password</label>
            <input id="j_password" name="j_password" type="password" /><br/>
            <button name="submit" type="submit" value="1" class="button">Login</button>
        </form>

    </s:layout-component>
</s:layout-render>