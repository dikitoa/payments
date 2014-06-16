<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
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
		<title><fmt:message key="title" /></title>
	</head>
  	<body>
  		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value="assets.htm"/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value="brokerage.htm"/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value="liabilities.htm"/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
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
    				<fmt:message key="modifyPin.heading"/>
    			</b>
    		</div>

    		<form:form method="POST" commandName="card">
    			<table>
    				<tr>
	<!--     			<td>PIN VIEJO:</td> -->
	<%--     			<td>${card.newPin}</td> --%>
    				</tr>
    				<tr>
    					<td>New PIN: </td>
    					<td><form:input path="newPin" maxlength="4" size="5"/></td>
    					<td><form:errors path="newPin" cssClass="error"/></td>
    				</tr>
    				<tr>
    					<td><input type="submit" value="Modificar"/></td>
    				</tr>
    			</table>
    		</form:form>

		    <a href="<c:url value="hello.htm"/>">Home</a>
		</div>
		</body>
</html>