<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<html lang="${language}">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Autorização de Usuário</title>
  <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
</head>
<body>
  <%@include file="internacionalizacao.jsp"%>
  <h1>Autorização de Usuário</h1>
  <c:if test="${mensagens.existeErros}">
    <div id="erro">
      <ul>
        <c:forEach var="erro" items="${mensagens.erros}">
          <li> ${erro} </li>
        </c:forEach>
      </ul>
    </div>
  </c:if>
</body>
</html>