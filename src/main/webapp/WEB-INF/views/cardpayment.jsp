<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
	<head>
		<title><fmt:message key="title" /></title>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
			.error { color: red; }
		</style>
		<style>
		.error {
			color: #ff0000;
		}

		.errorblock {
			color: #000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding: 8px;
			margin: 16px;
		}
		</style>
		<title><fmt:message key="makePayment" /></title>
	</head>
	<body>
		<header> 
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a> 
			<a class="optionsHeader assets" href="<c:url value=""/>"title="Assets"></a> 
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a> 
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a> 
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a> 
		</header>
		<div class="content"> 
 			<div class="headerContent"> 
	 			<b class="titleContent">
 					<fmt:message key="payment.title"/>
    			</b>
    		</div> 	

		<form:form method="post" commandName="paymentBean">
  				<table>
  				   	<tr>
		      			<td align="left" width="20%">Concept:</td>
        				<td width="20%">
		          			<form:input path="concept"/>
    	    			</td>
        				<td width="40%">
		          			<form:errors path="concept" cssClass="error"/>
    	    			</td>
    				</tr>
	    			<tr>
      					<td align="left" width="20%">Amount:</td>
        				<td width="20%">
	          				<form:input path="amount"/> &euro;
        				</td>
        				<td width="40%">
		          			<form:errors path="amount" cssClass="error"/>
        				</td>
    				</tr>
  				</table>
  				<br>
  				<input type="submit" value="Send Payment">
			</form:form>
			<a href="<c:url value="card.htm?id=${card.id}"/>">Prev</a>
		</div> 
	</body>
</html>