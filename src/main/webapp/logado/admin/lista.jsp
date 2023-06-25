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
      <fmt:message key="admins.welcome" />
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
      <a href="/${sessionScope.contextPath}/admin/cadastro">
        <fmt:message key="admins.create" />
      </a>
    </h2>
    <h3><fmt:message key="admins.list" /></h3>
    <br/>
  </div>
  <div align="center">
    <table border="1">
      <tr>
        <th><fmt:message key="admin.ID" /></th>
        <th><fmt:message key="admin.login" /></th>
        <th><fmt:message key="admin.password" /></th>
        <th><fmt:message key="admin.name" /></th>
        <th><fmt:message key="actions.link" /></th>
      </tr>
      <c:forEach var="admin" items="${requestScope.listaUsuarios}">
        <tr>
          <td><c:out value="${admin.id}" /></td>
          <td><c:out value="${admin.login}" /></td>
          <td><c:out value="${admin.senha}" /></td>
          <td><c:out value="${admin.nome}" /></td>
          <td><a
                  href="/${sessionScope.contextPath}/admin/edicao?id=<c:out value='${admin.id}' />">
            <fmt:message key="admins.update" />
          </a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a
                    href="/${sessionScope.contextPath}/admin/remocao?id=<c:out value='${admin.id}' />"
                    onclick="return confirm('<fmt:message key="confirm.link" />');">
              <fmt:message key="admins.delete" />
            </a>
          </td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</fmt:bundle>

</html>