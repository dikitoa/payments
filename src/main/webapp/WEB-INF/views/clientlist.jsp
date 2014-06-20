<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
		</style>
		<title><fmt:message key="searchClient" /></title>
	</head>
	
	<body>
		<header>
			<a class="backHome" href="<c:url value="startpage.htm"/>" title="Home"></a>
			<a class="optionsHeader assets" href="<c:url value=""/>" title="Assets"></a>
			<a class="optionsHeader brokerage" href="<c:url value=""/>" title="brokerage"></a>
			<a class="optionsHeader liabilities" href="<c:url value=""/>" title="Liabilities"></a>
			<a class="optionsHeader optionsHeaderSelected payments" href="<c:url value="cards.htm"/>" title="Payments"></a>
		</header>
		
		<nav class="menu">
			<ul>
				<li>
					<a href="<c:url value="addclient.htm"/>">Add client</a>
				</li>
				<li>
					<a class="selected" href="<c:url value="clientlist.htm"/>">Search client</a>
				</li>
			</ul>		
		</nav>
		
		<div class="content"> 
 			<div class="headerContent">
				<b class="titleContent"><fmt:message key="searchClient" /></b>
				<a class="searcherContent" href="<c:url value="searchclient.htm"/>" title="Search client"></a>
			</div>
			<c:forEach items="${model.clients}" var="client">
				<table>
					<tr>
						<td><i>NIF: </i></td>
						<td><c:out value="${client.id}" /></td>
					</tr>
					<tr>
						<td><i>Name: </i></td>
						<td><c:out value="${client.name}" /></td>
					</tr>
					<tr>
						<td><i>Surname: </i></td>
						<td><c:out value="${client.surnames}"></c:out></td>
					</tr>
					<tr>
						<td><i>Phone Number 1: </i></td>
						<td><c:out value="${client.phoneNumber1}"></c:out></td>
					</tr>
					<tr>
						<td><i>Address: </i></td>
						<td><c:out value="${client.address}"></c:out></td>
					</tr>
					<tr>
						<td><a href='<c:url value="client.htm?dni=${client.id}"></c:url>'>View data</a></td>
					</tr>
				</table>
			</c:forEach>
		</div>
	</body>
</html>