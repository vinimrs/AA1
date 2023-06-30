<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<html lang="${language}">
<fmt:bundle basename="message">

    <head>
        <title><fmt:message key="page.title" /></title>
    </head>

    <body>
    <%@include file="../../internacionalizacao.jsp"%>
    <div align="center">
        <h1>
            <fmt:message key="locacoes.welcome" />
        </h1>
        <h2>
            <a href="/${sessionScope.contextPath}/locacoes/cadastro">
                <fmt:message key="locacoes.create" />
            </a>
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout.jsp">
                <fmt:message key="exit.link" />
            </a>
        </h2>
        <br />
        <h3><fmt:message key="locacoes.list" /></h3>
        <br />
    </div>

    <div align="center">
        <table id="tabela-locacoes" border="1">
            <caption></caption>
            <tr>
                <th><fmt:message key="locacao.ID" /></th>
                <th><fmt:message key="locacao.date" /></th>
                <th><fmt:message key="locacao.hour" /></th>
                <th><fmt:message key="locacao.client.cpf" /></th>
                <th><fmt:message key="locacao.locadora.cnpj" /></th>
            </tr>
            <c:forEach var="locacao" items="${requestScope.listaLocacoes}">
                <tr>
                    <td>${locacao.id}</td>
                    <td><tags:localDate date="${locacao.data}" pattern="dd/MM/yyyy"/></td>
                    <td>${locacao.hora}</td>
                    <td>${locacao.cliente.cpf}</td>
                    <td>${locacao.locadora.cnpj}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</fmt:bundle>

</html>