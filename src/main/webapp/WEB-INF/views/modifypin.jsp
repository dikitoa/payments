<%@ include file="/WEB-INF/views/include.jsp"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><fmt:message key="title" /></title>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
			.error { color: red; }
		</style>
		<style>
		.error {
			color: #ff0000;
		}

		.errorblock {
			color: #000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
		</style>
		<title><fmt:message key="modifyPin" /></title>
	</head>
<body>
	<header> 
		<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a> 
		<a class="optionsHeader assets" href="<c:url value=""/>"title="Assets"></a> 
		<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a> 
		<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a> 
		<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a> 
	</header>
	
	<div class="content"> 
 			<div class="headerContent"> 
 				<b class="titleContent">
 					<fmt:message key="heading"/>
    			</b>
    		</div>
		<form:form method="POST" commandName="card">
			<table>
				<tr>
					<td>Insert PIN:</td>
					<td><form:input path="newPin" maxlength="4" size="5" /></td>
					<td><form:errors path="newPin" cssClass="error" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Modificar" /></td>
				</tr>
			</table>
		</form:form>
	</div>

	<a href="<c:url value="card.htm"/>">Prev</a>
	</body>
</html>