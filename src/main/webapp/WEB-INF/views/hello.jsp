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
					<a href="<c:url value="hello.htm"/>">Datos Tarjeta</a>
				</li>
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
 				<fmt:message key="heading-hello"/>
    		</b>
    	</div> 	
    	<b>Numero de tarjeta:</b> ${model.card.getId().toString()} 
    	<br/>
    	<b>PIN:</b> ${model.card.getPin()}
    	<br/>
		<b>Fecha de Caducidad:</b> ${model.card.getExpirationDate()}
		<br/>
     	<b>Limite Compras Diario:</b> ${model.card.getBuyLimitDiary()} &euro;
		<br/>
     	<b>Limite Compras Mensual:</b> ${model.card.getBuyLimitMonthly()} &euro;
     	<br/>
     	<b>Limite Efectivo Diario:</b> ${model.card.getCashLimitDiary()} &euro;
		<br/>
     	<b>Limite Efectivo Mensual:</b> ${model.card.getCashLimitMonthly()} &euro;
		<br/>
    </div> 
  </body>
</html>