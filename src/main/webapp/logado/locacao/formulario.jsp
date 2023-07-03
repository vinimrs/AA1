<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<html lang="${language}">
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="page.title" /></title>
        <link href="${pageContext.request.contextPath}/layout.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
    <%@include file="../../internacionalizacao.jsp"%>
    <div align="center">
        <h1>
            <fmt:message key="locacoes.create" />
        </h1>
        <c:if test="${mensagens.existeErros}">
            <div id="erro">
                <ul>
                    <c:forEach var="erro" items="${mensagens.erros}">
                        <li> ${erro} </li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <h2>
            <a href="lista"> <fmt:message key="locacoes.list" /></a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp"> <fmt:message
                    key="exit.link" />
            </a>
        </h2>
    </div>
    <div align="center">
        <form action="insercao" method="post">
            <%@include file="campos.jsp"%>
        </form>
    </div>
<%--    <c:if test="${!empty requestScope.mensagens}">--%>
<%--        <ul class="erro">--%>
<%--            <c:forEach items="${requestScope.mensagens}" var="mensagem">--%>
<%--                <li>${mensagem}</li>--%>
<%--            </c:forEach>--%>
<%--        </ul>--%>
<%--    </c:if>--%>
    </body>
</fmt:bundle>

</html>