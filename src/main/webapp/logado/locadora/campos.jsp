<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${locadora != null}">
                <fmt:message key="clients.update" />
            </c:when>
            <c:otherwise>
                <fmt:message key="clients.create" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:choose>
        <c:when test="${locadora != null}">
            <input type="hidden" name="id" value="<c:out value='${locadora.id}' />" />
            <input type="hidden" name="cnpj" size="20"
                   value="<c:out value='${locadora.cnpj}' />" />
            <input type="hidden" name="nome" size="45"
                   value="<c:out value='${locadora.nome}' />" />
        </c:when>
        <c:otherwise>
            <tr>
                <td><label for="cnpj"><fmt:message key="locadora.cnpj" />
                </label></td>
                <td><input id="cnpj" type="text" name="cnpj" size="20" required
                           value="<c:out value='${locadora.cnpj}' />" /></td>
            </tr>
            <tr>
                <td><label for="nome"><fmt:message key="locadora.name" />
                </label></td>
                <td><input id="nome" type="text" name="nome" size="45" required
                           value="<c:out value='${locadora.nome}' />" /></td>
            </tr>
        </c:otherwise>
    </c:choose>

    <tr>
        <td><label for="email"><fmt:message key="locadora.email" />
        </label></td>
        <td><input id="email" type="email" name="email" size="20" required
                   value="<c:out value='${locadora.email}' />" /></td>
    </tr>
    <tr>
        <td><label for="senha"><fmt:message key="locadora.password" />
        </label></td>
        <td><input id="senha" type="text" name="senha" size="20" required
                   value="<c:out value='${locadora.senha}' />" /></td>
    </tr>
    <tr>
        <td><label for="cidade"><fmt:message key="locadora.city" />
        </label></td>
        <td><input id="cidade" type="text" name="cidade" size="20" required
                   value="<c:out value='${locadora.cidade}' />" /></td>
    </tr>
<%--    <tr>--%>
<%--        <td><label for="papel"><fmt:message key="locadora.role" />--%>
<%--        </label></td>--%>
<%--        <td>--%>
<%--            <select name="papel">--%>
<%--                <option value="ADMIN" ${locadora.papel == "ADMIN" ? 'selected="selected"' : ''}>ADMIN</option>--%>
<%--                <option value="locadora" ${locadora.papel == "locadora" ? 'selected="selected"' : ''}>locadora</option>--%>
<%--            </select>--%>
<%--        </td>--%>
<%--    </tr>--%>
    <tr>
        <td colspan="2" align="center"><button id="submit-button" type="submit"
                                              value="<fmt:message key="save.link" />">
            <fmt:message key="locadora.submitButton" />
        </button></td>
    </tr>
</table>