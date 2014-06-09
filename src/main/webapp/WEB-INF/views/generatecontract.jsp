<%@ include file="/WEB-INF/views/include.jsp" %>


<html>
	<head>
		<title><fmt:message key="generateCardContractTitle"></fmt:message></title>
		<style>
			.error {
				color: #ff0000;
			}
			.errorblock{
				color: #000;
				background-color: #ffEEEE;
				border: 3px solid #ff0000;
				padding: 8px;
				margin: 16px;
			}
		</style>
	</head>
	
	<body>
		<h1><fmt:message key="contract-heading"/></h1>
	
		<form:form method="POST" commandName="Contract">
			<form:errors path="*" cssClass="errorBlock" element="div"/>
			
			<table>
				<tr>
					<td>Choose contract type: </td>
					<td>
						<form:select path="cardType">
							<form:option value="NONE" label="--select--"></form:option>
							<form:options items="${cardType}"/>
						</form:select>
					</td>
					<td><form:errors path="cardType" cssClass="error"></form:errors></td>
				</tr>
				<tr>
					<td><input type="submit" value="Generate"/></td>
				</tr>
			</table>
		</form:form>
		<a href='<c:url value="createcard.htm"></c:url>'>Prev</a>  <a href="<c:url value="cards.htm"/>">Home</a>
	</body>
</html>