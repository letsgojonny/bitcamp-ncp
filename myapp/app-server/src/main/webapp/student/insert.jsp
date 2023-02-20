
<%@page import="bitcamp.myapp.vo.Student"%>
<%@page import="bitcamp.myapp.dao.StudentDao"%>
<%@page import="bitcamp.myapp.dao.MemberDao"%>
<%@page import="bitcamp.util.TransactionManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;
  
  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    txManager = (TransactionManager) ctx.getAttribute("txManager");
    memberDao = (MemberDao) ctx.getAttribute("memberDao");
    studentDao = (StudentDao) ctx.getAttribute("studentDao");
  }
%>

<%
Student student = new Student();
    student.setName(request.getParameter("name"));
    student.setEmail(request.getParameter("email"));
    student.setPassword(request.getParameter("password"));
    student.setTel(request.getParameter("tel"));
    student.setPostNo(request.getParameter("postNo"));
    student.setBasicAddress(request.getParameter("basicAddress"));
    student.setDetailAddress(request.getParameter("detailAddress"));
    student.setWorking(request.getParameter("working") != null);
    student.setGender(request.getParameter("gender").charAt(0));
    student.setLevel(Byte.parseByte(request.getParameter("level")));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta http-equiv='Refresh' content='1;url=list.jsp'>
<title>비트캠프 - NCP 1기</title>
</head>
<body>
<h1>학생(JSP)</h1>
<%
      memberDao.insert(student);
      studentDao.insert(student);
%>
  <p>입력 했습니다.</p>
</body>
</html>
