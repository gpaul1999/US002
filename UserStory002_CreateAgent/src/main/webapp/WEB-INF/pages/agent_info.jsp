<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<div class="page-title">Agent Infor</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<table style="text-align: left;">
		<tr>
			<td>Agent Code</td>
			<td><a>${agentInfor.agentCode }</a></td>
		</tr>
		<tr>
			<td>Account Name</td>
			<td><a>${agentInfor.accountName }</a></td>
		</tr>
		<tr>
			<td>Account Type</td>
			<td><a>${agentInfor.accountType }</a></td>
		</tr>
		<tr>
			<td>Company Name</td>
			<td><a>${agentInfor.companyName }</a></td>
		</tr>
		<tr>
			<td>Company Code</td>
			<td><a>${agentInfor.companyCode }</a></td>
		</tr>
		<tr>
			<td>License Number</td>
			<td><a>${agentInfor.licenceNumber }</a></td>
		</tr>
		<tr>
			<td>Account Status</td>
			<td><a>${agentInfor.accountStatus }</a></td>
		</tr>
		<tr>
			<td></td>
			<td><security:authorize access="hasRole('ROLE_ADMIN')">
					<a
						href="${pageContext.request.contextPath}/edit_agent?agentCode=${agentInfor.agentCode}">
						Modify </a>
				</security:authorize></td>
		</tr>
	</table>



	<jsp:include page="footer.jsp" />
</body>
</html>