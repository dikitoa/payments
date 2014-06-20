<%@ include file="/WEB-INF/views/include.jsp" %>

<html>
	<head>
		<style type="text/css">
			<%@ include file ="/resources/css/style.css" %> 
		</style>
		<title><fmt:message key="clientData" /></title>
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
					<a href="<c:url value="cards.htm?dni=${client.dni}"/>">Client cards</a>
				</li>
				<li>
					<a href="<c:url value="clientlist.htm"/>">Search client</a>
				</li>
			</ul>		
		</nav>
		
		<div class="content"> 
 			<div class="headerContent">
				<b class="titleContent"><fmt:message key="clientData" /></b>
				<a class="searcherContent" href="<c:url value="searchclient.htm"/>" title="Search client"></a>
			</div>
			<table>
				<tr>
					<td><i>NIF: </i></td>
					<td><c:out value="${client.dni}" /></td>
				</tr>
				<tr>
					<td><i>Name: </i></td>
					<td><c:out value="${client.name}" /></td>
				</tr>
				<tr>
					<td><i>Surname: </i></td>
					<td><c:out value="${client.surnames}"></c:out><td>
				</tr>
				<tr>
					<td><i>Phone Number 1: </i></td>
					<td><c:out value="${client.phoneNumber1}"></c:out></td>
				</tr>
				<tr>
					<td><i>Phone Number 2: </i></td>
					<td><c:out value="${client.phoneNumber2}"></c:out></td>
				</tr>
				<tr>
					<td><i>Civil state: </i></td>
					<td><c:out value="${client.civilState}"></c:out></td>
				</tr>
				<tr>
					<td><i>Profession: </i></td>
					<td><c:out value="${client.profession}"></c:out></td>
				</tr>
				<tr>
					<td><i>Birth Date: </i></td>
					<td><c:out value="${client.birthDate}"></c:out></td>
				</tr>
				<tr>
					<td><i>Address: </i></td>
					<td><c:out value="${client.address}"></c:out></td>
				</tr>
			</table>
		</div>
	</body>
</html>