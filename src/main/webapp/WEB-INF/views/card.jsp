<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
			.error { color: red; }
		</style>
		<title><fmt:message key="showCardResultTitle" /></title>
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
					<a href="<c:url value="createcard.htm?id=${card.id}"/>">Nueva Tarjeta</a>
				</li>
				<li>
					<a href="<c:url value="modifypin.htm?id=${card.id}"/>">Modificar Pin</a>
				</li>
				<li>
					<a href="<c:url value="buyLimits.htm?id=${card.id}"/> ">Modificar L�mites Compra</a>
				</li>
				<li>
					<a href="<c:url value="cashLimits.htm?id=${card.id}"/>">Modificar L�mites Efectivo</a>
				</li>
				<li>
					<a href="<c:url value="feechange.htm?id=${card.id}"/>">Cambiar Comisiones</a>
				</li>
			</ul>
		</nav>
		<div class="content"> 
 			<div class="headerContent"> 
 				<b class="titleContent">
					<fmt:message key="card"/>
				</b>
			</div>
		
			<table>
				<tr>
					<td>Card Number: </td>
					<td>${card.id}</td>
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
				<tr>
					<td>Fees:</td>
					<td>${card.fees}</td>
				</tr>
			</table>
		</div>
	</body>
</html>