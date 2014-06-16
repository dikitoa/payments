<%@ include file="/WEB-INF/views/include.jsp"%>

<!DOCTYPE>
<html>

	<head>
		<style type="text/css">
			<%@include file="/resources/css/style.css" %>
	  	</style>
		<title><fmt:message key="title" /></title>
	</head>

	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value="startpage.htm"/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value="brokerage.htm"/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value="liabilities.htm"/>" title="Liabilities"></a>
			<a class="optionsHeader payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>

		<nav class="menu">
			<ul>
				<li>
					
				</li>
			</ul>
		</nav>
		
		<div class="content">
			<div class="headerContent">
				<b class="titleContent"><fmt:message key="searchClient"></fmt:message></b>
				<a class="searcherContent" href='<c:url value="searchclient.htm"></c:url>'></a>
			</div>
			<ul>
				<form:form method="post" commandName="searcher">
					<form:input path="dni" maxlength="9" size="20"/>
					<input type="submit" value="Search"/>
					<br><br>
					<p>Card list: </p><input type="submit" value="Cards"/>
				</form:form>
			</ul>
		</div>
	</body>

</html>