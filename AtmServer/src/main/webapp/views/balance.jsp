<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Operation balance</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:eval expression="commandNameEnum == T(com.home.atm.command.CommandName).PRINT" var="isValid"/>
<c:if test="${isValid}">
    <c:forEach var="printBalance" items="${operationResult}">
        On your balance is ${printBalance.balance} in currency ${printBalance.currency}!<br/>
    </c:forEach>
</c:if>
<h3><a href="${contextPath}/account"><input type="submit" value="Back to previous menu"/></a></h3>
</body>
</html>
