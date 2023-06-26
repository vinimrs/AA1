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
      <fmt:message key="locadoras.welcome" />
    </h1>
    <h2>
      <a href="/${sessionScope.contextPath}/editoras/gerenciar">
        <fmt:message key="publishers.entity" />
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="/${sessionScope.contextPath}/livros">
        <fmt:message key="books.entity" />
      </a>
      &nbsp;&nbsp;&nbsp;
      <a href="${pageContext.request.contextPath}/logout.jsp">
        <fmt:message key="exit.link" />
      </a>
      <br/>
      <br/>
      <a id="create-locadora-button" href="/${sessionScope.contextPath}/locadoras/cadastro">
        <fmt:message key="locadoras.create" />
      </a>
    </h2>
    <h3><fmt:message key="locadoras.list" /></h3>
    <br/>
  </div>
  <div align="center">
    <table border="1" id="tabela-locadoras">
      <tr>
        <th><fmt:message key="locadora.ID" /></th>
        <th><fmt:message key="locadora.email" /></th>
        <th><fmt:message key="locadora.name" /></th>
        <th><fmt:message key="locadora.cnpj" /></th>
        <th><fmt:message key="locadora.city" /></th>
        <th><fmt:message key="actions.link" /></th>
      </tr>
      <c:forEach var="locadora" items="${requestScope.listaClientes}">
        <tr>
          <td><c:out value="${locadora.id}" /></td>
          <td><c:out value="${locadora.email}" /></td>
          <td><c:out value="${locadora.nome}" /></td>
          <td><c:out value="${locadora.cnpj}" /></td>
          <td><c:out value="${locadora.cidade}" /></td>
          <td><a
                  href="/${sessionScope.contextPath}/locadoras/edicao?id=<c:out value='${locadora.id}' />">
            <fmt:message key="locadoras.update" />
          </a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a
                    href="/${sessionScope.contextPath}/locadoras/remocao?id=<c:out value='${locadora.id}' />"
                    onclick="return confirm('<fmt:message key="confirm.link" />');">
              <fmt:message key="locadoras.delete" />
            </a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</fmt:bundle>

</html>