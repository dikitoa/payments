<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<title><fmt:message key="createCardTitle"></fmt:message></title>
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
		<h1><fmt:message key="heading"/></h1>
		
		<form:form method="POST" commandName="newCard">
			<form:errors path="*" cssClass="errorBlock" element="div"/>
			
			<table>
				<tr>
				<td>Card Number: </td>
				<td><form:input path="cardNumber" maxlength="15" size="20"/></td>
				<td><form:errors path="cardNumber" cssClass="error"></form:errors></td>
				</tr>
				<tr>
				<td>Buy limit diary: </td>
				<td><form:input path="buyLimitDiary" size="15" onclick="clear"></form:input></td>
				<td><form:errors path="buyLimitDiary" cssClass="error"></form:errors></td>
				</tr>
				<tr>
				<td>Buy limit monthly: </td>
				<td><form:input path="buyLimitMonthly" size="15" onclick="clear"></form:input></td>
				<td><form:errors path="buyLimitMonthly" cssClass="error"></form:errors></td>
				</tr>
				<tr>
				<td>Cash limit diary: </td>
				<td><form:input path="cashLimitDiary" size="15" onclick="clear"/></td>
				<td><form:errors path="cashLimitDiary" cssClass="error"></form:errors></td>
				</tr>
				<tr>
				<td>Cash limit monthly: </td>
				<td><form:input path="cashLimitMonthly" size="15" onclick="clear"/></td>
				<td><form:errors path="cashLimitMonthly" cssClass="error"></form:errors></td>
				</tr>
				
				<tr>
				<td>Card type: </td>
				<td>
				<form:select path="cardType">
					<form:option value="NONE" label="--select--"></form:option>
					<form:options items="${cardType}"/>
				</form:select>
				</td>
				<td><form:errors path="cardType" cssClass="error"></form:errors></td>
				</tr>
				
				<tr>
				<td>Commission emissionn: </td>
				<td><form:input path="commissionEmission" size="15"/></td>
				<td><form:errors path="commissionEmission" cssClass="error"></form:errors></td>
				</tr>
				<tr>
				<td>Commission maintenance: </td>
				<td><form:input path="commissionMaintenance" size="15"/></td>
				<td><form:errors path="commissionMaintenance" cssClass="error"></form:errors></td>
				</tr>
				<tr>
				<td>Commission renovate: </td>
				<td><form:input path="commissionRenovate" size="15"/></td>
				<td><form:errors path="commissionRenovate" cssClass="error"></form:errors></td>
				</tr>
				
				<tr>
				<td>Contract: </td>
				<td><a href="<c:url value="generatecontract.htm"></c:url>">Terms and conditions</a><br></td>
				<td><form:checkbox path="contract"></form:checkbox>I acept conditions</td>
				<td><form:errors path="contract" cssClass="error"></form:errors></td>
				</tr>
				
				<tr>
				<td><input type="submit" value="Create"/></td>
				<td><input type="reset" value="Clear"/></td>
				</tr>
			</table>
		</form:form>
		<a href='<c:url value="cards.htm"/>'>Home</a>
	</body>
</html>