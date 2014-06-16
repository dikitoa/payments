<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
			.error { color: red; }
		</style>
		<title><fmt:message key="title" /></title>
	</head>
	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value="assets.htm"/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value="brokerage.htm"/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value="liabilities.htm"/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="payments.htm"/>" title="Payments"></a>
		</header>
	
		<nav class="menu">
			<ul>
				<li>
					<a href="<c:url value="createcard.htm"/>">Nueva Tarjeta</a>
				</li>
				<li>
					<a href="<c:url value="priceincrease.htm"/>">Modificar Pin</a>
				</li>
				<li>
					<a href="<c:url value="buyLimits.htm"/> ">Modificar Límites Compra</a>
				</li>
				<li>
					<a href="<c:url value="cashLimits.htm"/>">Modificar Límites Efectivo</a>
				</li>
				<li>
					<a href="<c:url value="feechange.htm"/>">Cambiar Comisiones</a>
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
					<td>Fees : </td>
					<td><c:out value="${card.fee}"></c:out></td>
					<td><a href="feechange.htm">Change Fee</a></td>
				</tr>
				</table>
			</c:forEach>
			<br><br>
			<a href="createcard.htm">New Card</a>
		</div>
	</body>
</html>