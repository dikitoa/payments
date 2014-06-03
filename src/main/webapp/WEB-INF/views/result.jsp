<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<title><fmt:message key="showCardResultTitle"></fmt:message></title>
	</head>
	<body>
		<h2><fmt:message key="cardResult"/></h2>
		
		<table>
			<tr>
				<td>User: </td>
				<td>${card.owner.name} ${card.owner.surname}</td>
			</tr>
			<tr>
				<td>Card Number: </td>
				<td>${card.id}</td>
			</tr>
			<tr>
				<td>Card Type:</td>
				<td>${card.cardType}</td>
			</tr>
			<tr>
				<td>PIN: </td>
				<td>${card.pin}</td>
			</tr>
			<tr>
				<td>CVV:</td>
				<td>${card.cvv}</td>
			</tr>
			<tr>
				<td>Emission Date:</td>
				<td>${card.emissionDate}</td>
			</tr>
			<tr>
				<td>Expiration Date:</td>
				<td>${card.expirationDate}</td>
			</tr>
			<tr>
				<td>Buy Limit Diary:</td>
				<td>${card.buyLimitDiary}</td>
			</tr>
			<tr>
				<td>Buy Limit Monthly:</td>
				<td>${card.buyLimitMonthly}</td>
			</tr>
			<tr>
				<td>Cash Limit Diary:</td>
				<td>${card.cashLimitDiary}</td>
			</tr>
			<tr>
				<td>Cash Limit Monthly:</td>
				<td>${card.cashLimitMonthly}</td>
			</tr>
		</table>
		<a href="<c:url value="cards.htm"/>">Home</a>
	</body>
</html>