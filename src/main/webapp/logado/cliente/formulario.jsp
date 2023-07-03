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
            <fmt:message key="clients.welcome" />
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
            <a href="${pageContext.request.contextPath}/logout.jsp">
                <fmt:message key="exit.link" />
            </a>
            <br/>
            <br/>
            <a href="lista">
                <fmt:message key="clients.list" />
            </a>
        </h2>
    </div>
    <div align="center">
        <c:choose>
            <c:when test="${cliente != null}">
                <form action="atualizacao" method="post">
                    <%@include file="campos.jsp"%>
                </form>
            </c:when>
            <c:otherwise>
                <form action="insercao" method="post">
                    <%@include file="campos.jsp"%>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
    </body>
</fmt:bundle>

</html>