<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Withdraw operation</title>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<spring:eval expression="errorCode == T(com.home.atm.exception.ErrorCodes).NOT_ENOUGH_MONEY" var="isError"/>
<c:if test="${isError}">
    Not enough money on the account!
</c:if>
<spring:eval expression="errorCode == T(com.home.atm.exception.ErrorCodes).NO_CURRENCY" var="isError"/>
<c:if test="${isError}">
    You don't have money on currency!
</c:if>
<spring:eval expression="errorCode == T(com.home.atm.exception.ErrorCodes).NO_MONEY_ON_THIS_CURRENCY" var="isError"/>
<c:if test="${isError}">
    You don't have money on this currency!
</c:if>
<spring:eval expression="commandNameEnum == T(com.home.atm.command.CommandName).WITHDRAW" var="isValid"/>
<c:if test="${isValid}">
    <c:forEach var="printWithdraw" items="${operationResult}">
        Removed from your account ${printWithdraw.balance} in currency ${printWithdraw.currency}!<br/>
    </c:forEach>
</c:if>
<h3><a href="{contextPath}/account"><input type="submit" value="Back to previous menu"/></a></h3>
</body>
</html>
