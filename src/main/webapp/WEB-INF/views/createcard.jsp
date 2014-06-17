<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
			.error { color: red; }
		</style>
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
		<title><fmt:message key="createCardTitle" /></title>
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
 					<fmt:message key="heading"/>
    			</b>
    		</div> 	
		
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
						<td><form:input path="fee" size="15"/></td>
						<td><form:errors path="fee" cssClass="error"></form:errors></td>
					</tr>
					<tr>
						<td>Contract: </td>
						<td><a href="<c:url value="generatecontract.htm"></c:url>">Terms and conditions</a><br></td>
						<td><form:checkbox path="contract"></form:checkbox>I acept conditions</td>
						<td><form:errors path="contract" cssClass="error"></form:errors></td>
					</tr>
					
					<form:hidden path="dni"/>
					<form:hidden path="accountNumber"/>
				
					<tr>
						<td><input type="submit" value="Create"/></td>
						<td><input type="reset" value="Clear"/></td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>