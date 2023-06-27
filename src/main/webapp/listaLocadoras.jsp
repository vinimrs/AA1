<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

  <head>
    <title><fmt:message key="page.title" /></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
  </head>

  <body>
  <div align="center">
    <h3><fmt:message key="locadoras.list" /></h3>
    <h2>
      <a href="${pageContext.request.contextPath}/">
        <fmt:message key="login.link" />
      </a>
    </h2>
    <br/>
  </div>
  <c:if test="${mensagens.existeErros}">
    <div id="erro">
      <ul>
        <c:forEach var="erro" items="${mensagens.erros}">
          <li> ${erro} </li>
        </c:forEach>
      </ul>
    </div>
  </c:if>
  <div align="center">
    <table border="1">
      <tr>
        <th><fmt:message key="locadora.ID" /></th>
        <th><fmt:message key="locadora.name" /></th>
        <th><fmt:message key="locadora.city" /></th>

<%--        <th><fmt:message key="actions.link" /></th>--%>
      </tr>
      <c:forEach var="locadora" items="${requestScope.listaLocadoras}">
        <tr>
          <td><c:out value="${locadora.id}" /></td>
          <td><c:out value="${locadora.nome}" /></td>
          <td><c:out value="${locadora.cidade}" /></td>
<%--          <td><a--%>
<%--                  href="/${sessionScope.contextPath}/locadoras/edicao?id=<c:out value='${locadora.id}' />">--%>
<%--            <fmt:message key="locadoras.update" />--%>
<%--          </a> <c:if test="${locadora.qtdeLivros == 0}">--%>
<%--            &nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--            <a--%>
<%--                    href="/${sessionScope.contextPath}/locadoras/remocao?id=<c:out value='${locadora.id}' />"--%>
<%--                    onclick="return confirm('<fmt:message key="confirm.link" />');">--%>
<%--              <fmt:message key="locadoras.delete" />--%>
<%--            </a>--%>
<%--          </c:if></td>--%>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</fmt:bundle>

</html>