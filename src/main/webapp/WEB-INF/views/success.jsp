<%@ include file="/WEB-INF/views/include.jsp"%>

<!DOCTYPE>
<html>

	<head>
		<style type="text/css">
			<%@include file="/resources/css/style.css" %>
	  	</style>
		<title><fmt:message key="resultTitle" /></title>
	</head>

	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value=""/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a>
			<a class="optionsHeader payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>

		<nav class="menu">
			<ul>
				<li>
					<p><fmt:message key="operationSuccess"></fmt:message></p>
				</li>
				<li>
					<a href='<c:url value="card.htm"></c:url>'>Return Home</a>
				</li>
			</ul>
		</nav>
	</body>

</html>