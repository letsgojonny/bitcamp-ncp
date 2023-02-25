package bitcamp.myapp.controller.TeacherController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.controller.PageController;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.vo.Teacher;

public class TeacherInsertController implements PageController {

  private TeacherService teacherService;

  public TeacherInsertController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    Teacher teacher = new Teacher();
    teacher.setName(request.getParameter("name"));
    teacher.setEmail(request.getParameter("email"));
    teacher.setPassword(request.getParameter("password"));
    teacher.setTel(request.getParameter("tel"));
    teacher.setDegree(Integer.parseInt(request.getParameter("degree")));
    teacher.setSchool(request.getParameter("school"));
    teacher.setMajor(request.getParameter("major"));
    teacher.setWage(Integer.parseInt(request.getParameter("wage")));

    try {
      teacherService.add(teacher);

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", "other");
    }
    return "/teacher/insert.jsp";
  }

}
