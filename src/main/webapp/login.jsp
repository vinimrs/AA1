<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<!DOCTYPE html>
<html lang="${language}">
<fmt:bundle basename="message">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="page.title" /></title>
    <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
  <%@include file="internacionalizacao.jsp"%>
  <h1><fmt:message key="user.welcome" /></h1>
  <c:if test="${mensagens.existeErros}">
    <div id="erro">
      <ul>
        <c:forEach var="erro" items="${mensagens.erros}">
          <li> ${erro} </li>
        </c:forEach>
      </ul>
    </div>
  </c:if>
  <form method="post" action="index.jsp">
    <table>
      <tr>
        <th><fmt:message key="user.login" />:</th>
        <td><input id="login" type="text" name="login"
                   value="${param.login}"/></td>
      </tr>
      <tr>
        <th><fmt:message key="user.password" />:</th>
        <td><input id="password" type="password" name="senha" /></td>
      </tr>
      <tr>
        <td colspan="2">
          <input id="input-submit" type="submit" name="bOK" value="<fmt:message key="user.login"/>">
        </td>
      </tr>
    </table>
  </form>
  </body>
</fmt:bundle>
</html>