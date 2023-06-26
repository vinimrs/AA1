<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${cliente != null}">
                <fmt:message key="clients.update" />
            </c:when>
            <c:otherwise>
                <fmt:message key="clients.create" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:choose>
        <c:when test="${cliente != null}">
            <input type="hidden" name="id" value="<c:out value='${cliente.id}' />" />
            <input type="hidden" name="cpf" size="20"
                   value="<c:out value='${cliente.cpf}' />" />
            <input type="hidden" name="nome" size="45"
                   value="<c:out value='${cliente.nome}' />" />
        </c:when>
        <c:otherwise>
            <tr>
                <td><label for="cpf"><fmt:message key="client.cpf" />
                </label></td>
                <td><input id="cpf" type="text" name="cpf" size="20" required
                           value="<c:out value='${cliente.cpf}' />" /></td>
            </tr>
            <tr>
                <td><label for="nome"><fmt:message key="client.name" />
                </label></td>
                <td><input id="nome" type="text" name="nome" size="45" required
                           value="<c:out value='${cliente.nome}' />" /></td>
            </tr>
        </c:otherwise>
    </c:choose>

    <tr>
        <td><label for="email"><fmt:message key="client.email" />
        </label></td>
        <td><input id="email" type="email" name="email" size="20" required
                   value="<c:out value='${cliente.email}' />" /></td>
    </tr>
    <tr>
        <td><label for="senha"><fmt:message key="client.password" />
        </label></td>
        <td><input id="senha" type="text" name="senha" size="20" required
                   value="<c:out value='${cliente.senha}' />" /></td>
    </tr>
    <tr>
        <td><label for="telefone"><fmt:message key="client.phone" />
        </label></td>
        <td><input id="telefone" type="text" name="telefone" size="20" required
                   value="<c:out value='${cliente.telefone}' />" /></td>
    </tr>
    <c:choose>
        <c:when test="${cliente != null}">
            <input type="hidden" name="sexo" size="20"
                   value="<c:out value='${cliente.sexo}' />" />
            <input type="hidden" name="dataNascimento" size="20"
                   value="<c:out value='${cliente.dataNascimento}' />" />
        </c:when>
        <c:otherwise>
            <tr>
                <td><label for="sexo"><fmt:message key="client.sex" />
                </label></td>
                <td><input id="sexo" type="text" name="sexo" size="20" required
                           value="<c:out value='${cliente.sexo}' />" /></td>
            </tr>
            <tr>
                <td><label for="dataNascimento"><fmt:message key="client.bornDate" />
                </label></td>
                <td><input id="dataNascimento" type="date" name="dataNascimento" size="20" required
                           value="<c:out value='${cliente.dataNascimento}' />" /></td>
            </tr>
        </c:otherwise>
    </c:choose>
<%--    <tr>--%>
<%--        <td><label for="papel"><fmt:message key="cliente.role" />--%>
<%--        </label></td>--%>
<%--        <td>--%>
<%--            <select name="papel">--%>
<%--                <option value="ADMIN" ${cliente.papel == "ADMIN" ? 'selected="selected"' : ''}>ADMIN</option>--%>
<%--                <option value="cliente" ${cliente.papel == "cliente" ? 'selected="selected"' : ''}>cliente</option>--%>
<%--            </select>--%>
<%--        </td>--%>
<%--    </tr>--%>
    <tr>
        <td colspan="2" align="center"><button id="submit-button" type="submit"
                                              value="<fmt:message key="save.link" />">
            <fmt:message key="client.submitButton" />
        </button></td>
    </tr>
</table>