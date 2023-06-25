<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${admin != null}">
                <fmt:message key="admins.update" />
            </c:when>
            <c:otherwise>
                <fmt:message key="admins.create" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${admin != null}">
        <input type="hidden" name="id" value="<c:out value='${admin.id}' />" />
    </c:if>
    <tr>
        <td><label for="nome"><fmt:message key="admin.name" />
        </label></td>
        <td><input id="nome" type="text" name="nome" size="45" required
                   value="<c:out value='${admin.nome}' />" /></td>
    </tr>
    <tr>
        <td><label for="login"><fmt:message key="admin.login" />
        </label></td>
        <td><input id="login" type="text" name="login" size="20" required
                   value="<c:out value='${admin.login}' />" /></td>
    </tr>
    <tr>
        <td><label for="senha"><fmt:message key="admin.password" />
        </label></td>
        <td><input id="senha" type="text" name="senha" size="20" required
                   value="<c:out value='${admin.senha}' />" /></td>
    </tr>
<%--    <tr>--%>
<%--        <td><label for="papel"><fmt:message key="admin.role" />--%>
<%--        </label></td>--%>
<%--        <td>--%>
<%--            <select name="papel">--%>
<%--                <option value="ADMIN" ${admin.papel == "ADMIN" ? 'selected="selected"' : ''}>ADMIN</option>--%>
<%--                <option value="admin" ${admin.papel == "admin" ? 'selected="selected"' : ''}>admin</option>--%>
<%--            </select>--%>
<%--        </td>--%>
<%--    </tr>--%>
    <tr>
        <td colspan="2" align="center"><button type="submit"
                                              value="<fmt:message key="save.link" />">
            <fmt:message key="admin.submitButton" />
        </button></td>
    </tr>
</table>