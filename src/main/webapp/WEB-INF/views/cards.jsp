<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<title><fmt:message key="title"></fmt:message></title>
	</head>
	<body>
		<h1><fmt:message key="cards"></fmt:message></h1>
		
		<c:forEach items="${model.cards}" var="card"><br>
			<table>
			<tr>
				<td>Card Number: </td>
				<td><c:out value="${card.id}"></c:out></td>
				<td><a href="">Delete Card</a></td>
			</tr>
			<tr>
				<td>Type: </td>
				<td><c:out value="${card.cardType}"></c:out></td>
			</tr>
			<tr>
				<td>PIN: </td>
				<td><c:out value="${card.pin}"></c:out></td> 
				<td><a href="">Modify PIN</a></td>
			</tr>
			<tr>
				<td>Buy limit diary: </td>
				<td><c:out value="${card.buyLimitDiary}"></c:out></td> 
				<td><a href="buyLimits.htm">Modify Buy Limits</a></td>
			</tr>
			<tr>
				<td>Buy limit monthly: </td>
				<td><c:out value="${card.buyLimitMonthly}"></c:out></td> 
			</tr>
			<tr>
				<td>Cash limit diary: </td>
				<td><c:out value="${card.cashLimitDiary}"></c:out></td>
				<td><a href="cashLimits.htm">Modify Cash Limits</a></td>
			</tr>
			<tr>
				<td>Cash limit monthly: </td>
				<td><c:out value="${card.cashLimitMonthly}"></c:out></td> 
			</tr>
			<tr>
				<td>Comission Emission: </td>
				<td><c:out value="${card.commissionEmission}"></c:out></td>
				<td><a href="<c:url value="comissionchange.htm"/>">Modify Comissions</</a></td>
			</tr>
			<tr>
				<td>Comission Maintenance: </td>
				<td><c:out value="${card.commissionMaintenance}"></c:out></td> 
			</tr>
			<tr>
				<td>Comission Renovate: </td>
				<td><c:out value="${card.commissionRenovate}"></c:out></td> 
			</tr>
			</table>
		</c:forEach>
		<br><br>
		<a href="createcard.htm">New Card</a>
	</body>
</html>