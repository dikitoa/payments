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
			<a class="optionsHeader assets" href="<c:url value="assets.htm"/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value="brokerage.htm"/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value="liabilities.htm"/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="payments.htm"/>" title="Payments"></a>
		</header>
	
		<nav class="menu">
			<ul>
				<li>
					<a href="<c:url value=""/>">Opcion</a>
				</li>
				<li>
					<a href="<c:url value=""/> ">Opcion</a>
				</li>
				<li>
					<a href="<c:url value=""/>">Opcion</a>
				</li>
				<li>
					<a href="<c:url value=""/>">Opcion</a>
				</li>
				<li>
					<a href="<c:url value=""/>">Opcion</a>
				</li>
				<li>
					<a href="<c:url value=""/>">Opcion</a>
				</li>
			</ul>
		</nav>
			
		<h1><fmt:message key="cashLimits.title"/></h1>
		
		<form:form method="post" commandName="changeLimit">
  			<table width="95%" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
    			<tr>
      				<td align="right" width="20%">Diary Limit:</td>
        			<td width="20%">
          				<form:input path="diaryLimit"/>
        			</td>
        			<td width="60%">
          				<form:errors path="diaryLimit" cssClass="error"/>
        			</td>
    			</tr>
    			<tr>
      				<td align="right" width="20%">Monthly Limit:</td>
        			<td width="20%">
          				<form:input path="monthlyLimit"/>
        			</td>
        			<td width="60%">
          				<form:errors path="monthlyLimit" cssClass="error"/>
        			</td>
    			</tr>
  			</table>
  			<br>
  			<input type="submit" value="Save Changes">
		</form:form>
		<a href="<c:url value="cards.htm"/>">Home</a>
	</body>
</html>