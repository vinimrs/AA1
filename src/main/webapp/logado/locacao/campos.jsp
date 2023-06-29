<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalTime" %>
<%@ page import="org.openqa.selenium.html5.LocalStorage" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table id="tabela-locadoras" border="1" style="width: 400px; border: 1px solid black">

  <tr>
    <th></th>
    <th><fmt:message key="locacao.name" /></th>
    <th><fmt:message key="locacao.email" /></th>
    <th><fmt:message key="locacao.city" /></th>
  </tr>
  <c:forEach var="locadora" items="${listaLocadoras}">
    <tr>
      <td style="width: 10%; text-align: center"><input type="radio"
                      name="cnpj" value="${locadora.cnpj}" required></td>
      <td>${locadora.nome}</td>
      <td>${locadora.email}</td>
      <td>${locadora.cidade}</td>
    </tr>
  </c:forEach>
</table>

<%
  String minDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

  String actualHour = LocalTime.now().toString().substring(0,2);
  String minHour = null;
  if(actualHour.equals("23")) {
    minHour = "00:00";
  } else if (actualHour.equals("09")) {
    minHour = "10:00";
  } else if (actualHour.equals("19")) {
    minHour = "20:00";
  } else {
    String segundaCasa = LocalTime.now().toString().substring(1, 2);
    Integer next = Integer.parseInt(segundaCasa) + 1;
    String primeiraCasa = LocalTime.now().toString().substring(0,1);
    minHour = primeiraCasa.concat(next.toString()).concat(":00");
  }
%>

<input id="data" type="date" name="data" min="<%=minDate%>" value="<%=minDate%>" />
<%--<input type="time" step="3600000" />--%>
<input id="hora" type="time" step="3600" name="hora" min="<%=minHour%>" value="<%=minHour%>" >
<input type="hidden" name="cpf" value="${usuarioLogado.cpf}">
<br/>
<br/>

<tr>
  <td colspan="2" align="center"><input id="submit-button" type="submit"
                                        value="<fmt:message key="save.link" />" /></td>
</tr>