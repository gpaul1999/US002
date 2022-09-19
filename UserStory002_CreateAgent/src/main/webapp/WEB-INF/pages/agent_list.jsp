<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<base href="${pageContext.request.contextPath }/">
<title>User Story 002 - Create Agent</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/style.css" />" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<jsp:include page="menu.jsp" />

	<div class="page-title">Current Agent List</div>

	<table border="1" style="width: 100%">
		<tr>
			<th>Agent Code</th>
			<th>Account Name</th>
			<th>Company Name</th>
			<th>Account Type</th>
		</tr>
		<c:forEach items="${listAgent }" var="lsAgent">
			<tr>
				<th>${lsAgent.agentCode }</th>
				<th>${lsAgent.accountName }</th>
				<th>${lsAgent.companyName }</th>
				<th>${lsAgent.accountType }</th>
				<th><td><a
					href="${pageContext.request.contextPath}/agent_info?agentCode=${lsAgent.agentCode}">
						Inquire</a></td></th>

				<th><security:authorize access="hasRole('ROLE_ADMIN')">
						<a href="${pageContext.request.contextPath}/edit_agent?agentCode=${lsAgent.agentCode}"> Modify
						</a>
					</security:authorize></th>
			</tr>
		</c:forEach>
	</table>

	<jsp:include page="footer.jsp" />
</body>
</html>