<%@ include file="/WEB-INF/views/include.jsp" %>
<html>
  <head><title><fmt:message key="title"/></title></head>
  <body>
    <h1><fmt:message key="heading"/></h1>
    <p><fmt:message key="greeting"/></p>
    <h3>Cards</h3>
    <p>Numero de tarjeta: ${card.cardNumber} </p>
    <p>PIN: ${card.pin}</p>
<%--     <p>Fecha de Caducidad: ${model.expirationDate}</p> --%>
<%--     <p>Limite Diario: ${model.buyLimitDiary}</p> --%>
    <br>
    <a href="<c:url value="priceincrease.htm"/>">Modificar Pin</a>
    <br>
  </body>
</html>