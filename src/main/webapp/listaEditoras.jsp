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
    <h3><fmt:message key="publishers.list" /></h3>
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
        <th><fmt:message key="publisher.ID" /></th>
        <th><fmt:message key="publisher.CNPJ" /></th>
        <th><fmt:message key="publisher.name" /></th>
        <th><fmt:message key="actions.link" /></th>
      </tr>
      <c:forEach var="editora" items="${requestScope.listaEditoras}">
        <tr>
          <td><c:out value="${editora.id}" /></td>
          <td><c:out value="${editora.CNPJ}" /></td>
          <td><c:out value="${editora.nome}" /></td>
          <td><a
                  href="/${sessionScope.contextPath}/editoras/edicao?id=<c:out value='${editora.id}' />">
            <fmt:message key="publishers.update" />
          </a> <c:if test="${editora.qtdeLivros == 0}">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a
                    href="/${sessionScope.contextPath}/editoras/remocao?id=<c:out value='${editora.id}' />"
                    onclick="return confirm('<fmt:message key="confirm.link" />');">
              <fmt:message key="publishers.delete" />
            </a>
          </c:if></td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</fmt:bundle>

</html>