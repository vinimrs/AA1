<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

  <head>
    <title><fmt:message key="page.title" /></title>
  </head>

  <body>
  <div align="center">
    <h1>
      <fmt:message key="clients.welcome" />
    </h1>
    <h2>
      <a id="link-locadoras" href="/${sessionScope.contextPath}/locadoras/gerenciamento">
        <fmt:message key="locadoras.entity" />
      </a>
      &nbsp;&nbsp;&nbsp;
      <a id="link-locacoes" href="/${sessionScope.contextPath}/locacoes/gerenciamento">
        <fmt:message key="locacoes.entity" />
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="${pageContext.request.contextPath}/logout.jsp">
        <fmt:message key="exit.link" />
      </a>
      <br/>
      <br/>
      <a id="create-client-button" href="/${sessionScope.contextPath}/clientes/cadastro">
        <fmt:message key="clients.create" />
      </a>
    </h2>
    <h3><fmt:message key="clients.list" /></h3>
    <br/>
  </div>
  <div align="center">
    <table border="1" id="tabela-clientes">
      <tr>
        <th><fmt:message key="client.ID" /></th>
        <th><fmt:message key="client.email" /></th>
        <th><fmt:message key="client.name" /></th>
        <th><fmt:message key="client.cpf" /></th>
        <th><fmt:message key="client.phone" /></th>
        <th><fmt:message key="actions.link" /></th>
      </tr>
      <c:forEach var="client" items="${requestScope.listaClientes}">
        <tr>
          <td><c:out value="${client.id}" /></td>
          <td><c:out value="${client.login}" /></td>
          <td><c:out value="${client.nome}" /></td>
          <td><c:out value="${client.cpf}" /></td>
          <td><c:out value="${client.telefone}" /></td>
          <td><a
                  href="/${sessionScope.contextPath}/clientes/edicao?id=<c:out value='${client.id}' />">
            <fmt:message key="clients.update" />
          </a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a
                    href="/${sessionScope.contextPath}/clientes/remocao?id=<c:out value='${client.id}' />"
                    onclick="return confirm('<fmt:message key="confirm.link" />');">
              <fmt:message key="clients.delete" />
            </a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</fmt:bundle>

</html>