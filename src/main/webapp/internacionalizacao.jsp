<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<div align="end">
  <form>
    <select id="language" name="language" onchange="submit()">
      <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
      <option value="pt" ${language == 'pt' ? 'selected' : ''}>Português</option>
    </select>
  </form>
</div>
