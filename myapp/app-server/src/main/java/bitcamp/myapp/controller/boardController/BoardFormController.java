package bitcamp.myapp.controller.boardController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bitcamp.myapp.controller.PageController;

public class BoardFormController implements PageController {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {
    return "/board/form.jsp";
  }
}








