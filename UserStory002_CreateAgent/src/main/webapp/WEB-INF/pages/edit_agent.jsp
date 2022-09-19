<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

	<div class="page-title">Add Agent</div>
	<c:if test="${not empty errorMessage }">
		<div class="error-message">${errorMessage }</div>
	</c:if>

	<form:form modelAttribute="agentForm" method="POST"
		enctype="multipart/form-data">
		<table style="text-align: left;">
			<form:input path="agentCode" value="${agentInfor.agentCode }"
				type="hidden" />
			<tr>
				<td>Account Name *</td>
				<td><form:input path="accountName"
						value="${agentInfor.accountName }" /></td>
				<td><form:errors path="accountName" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Company Name *</td>
				<td><form:input path="companyName"
						value="${agentInfor.companyName }" /></td>
				<td><form:errors path="companyName" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Company Code *</td>
				<td><form:input path="companyCode"
						value="${agentInfor.companyCode }" /></td>
				<td><form:errors path="companyCode" class="error-message"></form:errors>
				</td>
			</tr>
			<tr>
				<td>Account Type *</td>
				<td>${agentInfor.accountType }</td>
				<form:input path="accountType" value="${agentInfor.accountType }"
					type="hidden" />
				<td><form:errors path="accountType" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>License Number *</td>
				<td><form:input path="licenceNumber"
						value="${agentInfor.licenceNumber }" /></td>
				<td><form:errors path="licenceNumber" class="error-message" /></td>
			</tr>
			<tr>
				<td>Account Status *</td>
				<td><form:select path="accountStatus">
						<form:option value="NONE">Select</form:option>
						<form:options items="${lsStatus }"></form:options>
					</form:select></td>
				<td><form:errors path="accountStatus" class="error-message"></form:errors></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit"></td>

			</tr>
		</table>
	</form:form>



	<jsp:include page="footer.jsp" />
</body>
</html>