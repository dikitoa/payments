<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
			.error { color: red; }
		</style>
		<title><fmt:message key="cardTitle" /></title>
	</head>
	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value=""/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>
	
		<nav class="menu">
			<ul>
				<li>
					<a href="<c:url value="createcard.htm?dni=${model.dni}"/>">Nueva Tarjeta</a>
				</li>
			</ul>
		</nav>

    	<div class="content"> 
 			<div class="headerContent"> 
 				<b class="titleContent">
 					<fmt:message key="cards"/>
    			</b>
    		</div> 
			<c:forEach items="${model.cards}" var="card"><br>
				<table>
				<tr>
					<td>Card Number: </td>
					<td><c:out value="${card.id}"></c:out></td>
				</tr>
				<tr>
					<td>Type: </td>
					<td><c:out value="${card.discriminator}"></c:out></td>
				</tr>
				<tr>
					<td>PIN: </td>
					<td><c:out value="${card.pin}"></c:out></td> 
				</tr>
				<tr>
					<td>Buy limit diary: </td>
					<td><c:out value="${card.buyLimitDiary}"></c:out></td> 
				</tr>
				<tr>
					<td>Buy limit monthly: </td>
					<td><c:out value="${card.buyLimitMonthly}"></c:out></td> 
				</tr>
				<tr>
					<td>Cash limit diary: </td>
					<td><c:out value="${card.cashLimitDiary}"></c:out></td>
				</tr>
				<tr>
					<td>Cash limit monthly: </td>
					<td><c:out value="${card.cashLimitMonthly}"></c:out></td> 
				</tr>
				<tr>
					<td>Fees : </td>
					<td><c:out value="${card.fees}"></c:out></td>
				</tr>
				<tr>
					<td><a href="<c:url value="card.htm?id=${card.id}"/>">Edit card</a></td>
				</tr>
				</table>
			</c:forEach>
		</div>
	</body>
</html>