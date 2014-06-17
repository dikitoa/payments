<%@ include file="/WEB-INF/views/include.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
			<a class="optionsHeader assets" href="<c:url value=""/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>
	
		<div class="content"> 
 			<div class="headerContent"> 
 				<b class="titleContent">
 					<fmt:message key="comissions"/>
    			</b>
    		</div> 	
		
			<form:form method="post" commandName="feeChange">
  				<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
		    		<tr>
      					<td align="right" width="20%">Change Fee:</td>
        				<td width="20%">
		          			<form:input path="fees"/>
        				</td>
        				<td width="60%">
		          			<form:errors path="fees" cssClass="error"/>
        				</td>
    				</tr>
  				</table>
  				<br>
  				<input type="submit" value="Save Fee Change">
			</form:form>
			<a href="<c:url value="card.htm"/>">Prev</a>
		</div>
	</body>
</html>