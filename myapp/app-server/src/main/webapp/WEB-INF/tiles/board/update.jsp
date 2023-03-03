<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1>게시판(Tiles)</h1>

<c:choose>
  <c:when test="${empty error}">
      <p>변경했습니다.</p>
  </c:when>
  <c:when test="${error == 'data'}">
      <p>해당 번호의 게시글이 없습니다.</p>
  </c:when>
  <c:otherwise>
      <p>변경 실패입니다.</p>
  </c:otherwise>  
</c:choose>


