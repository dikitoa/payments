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
 					<fmt:message key="buyLimits.title"/>
    			</b>
    		</div> 	

			<form:form method="post" commandName="changeLimit">
  				<table width="95%" border="0" cellspacing="0" cellpadding="5">
	    			<tr>
      					<td align="left" width="20%">Diary Limit:</td>
        				<td width="20%">
	          				<form:input path="diaryLimit"/>
        				</td>
        				<td width="40%">
		          			<form:errors path="diaryLimit" cssClass="error"/>
        				</td>
    				</tr>
    				<tr>
		      			<td align="left" width="20%">Monthly Limit:</td>
        				<td width="20%">
		          			<form:input path="monthlyLimit"/>
    	    			</td>
        				<td width="40%">
		          			<form:errors path="monthlyLimit" cssClass="error"/>
    	    			</td>
    				</tr>
  				</table>
  				<br>
  				<input type="submit" value="Save Changes">
			</form:form>
			<a href="<c:url value="card.htm"/>">Prev</a>
		</div> 
	</body>
</html>