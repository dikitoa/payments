<%@ include file="/WEB-INF/views/include.jsp" %>


<html>
	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value=""/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>
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