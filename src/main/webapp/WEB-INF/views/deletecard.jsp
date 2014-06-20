<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
		</style>
		<title><fmt:message key="deleteCardTitle" /></title>
	</head>
	
	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value=""/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>
		
		<div class="content"> 
 			<div class="headerContent"> 
 				<b class="titleContent">
 					<fmt:message key="deleteCardHeading"/>
    			</b>
    		</div> 	
		
			<form:form method="POST" commandName="newCard">
				
				<p>Are you sure want to delete this card?</p>
				<p><c:out value="${id}"/></p>
				<br><br>
				<input type="submit" name="Yes"/>  <a href='<c:url value="cards.htm"></c:url>'>Home</a>
			</form:form>
		</div>
	</body>
</html>