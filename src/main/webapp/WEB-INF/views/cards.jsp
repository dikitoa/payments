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
				<td><a href="">Modify Buy Limits</a></td>
			</tr>
			<tr>
				<td>Buy limit monthly: </td>
				<td><c:out value="${card.buyLimitMonthly}"></c:out></td> 
			</tr>
			<tr>
				<td>Cash limit diary: </td>
				<td><c:out value="${card.cashLimitDiary}"></c:out></td>
				<td><a href="">Modify Cash Limits</a></td>
			</tr>
			<tr>
				<td>Cash limit monthly: </td>
				<td><c:out value="${card.cashLimitMonthly}"></c:out></td> 
			</tr>
			</table>
		</c:forEach>
		<br><br>
		<a href="createcard.htm">New Card</a>
		<button type="button"
		onClick="location.href='<c:url value="buyLimits.htm"/>'">Change
		Buy Limits</button> 
	<button type="button"
		onClick="location.href='<c:url value="cashLimits.htm"/>'">Change
		Cash Limits</button>
	</body>
</html>