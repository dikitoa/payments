<%@ include file="/WEB-INF/views/include.jsp"%>

<html>
<head>
<title><fmt:message key="title"></fmt:message></title>
</head>
<body>
	<h1>
		<fmt:message key="comissions"></fmt:message>
	</h1>
	<h2></h2>

	<br>
	<table>
			<tr>
				<td>Comission Emission: </td>
				<td><c:out value="${card.commissionEmission}"></c:out></td>
			
			</tr>
			<tr>
				<td>Comission Maintenance: </td>
				<td><c:out value="${card.commissionMaintenance}"></c:out></td> 
			</tr>
			<tr>
				<td>Comission Renovate: </td>
				<td><c:out value="${card.commissionRenovate}"></c:out></td> 
			</tr>
	</table>

	<br>
	<br>

</body>
</html>