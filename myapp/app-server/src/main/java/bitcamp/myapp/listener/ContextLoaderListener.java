package bitcamp.myapp.listener;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
<<<<<<< HEAD
=======
import bitcamp.myapp.controller.AuthFailController;
import bitcamp.myapp.controller.LoginController;
import bitcamp.myapp.controller.LoginFormController;
import bitcamp.myapp.controller.LogoutController;
import bitcamp.myapp.controller.TeacherController.TeacherDeleteController;
import bitcamp.myapp.controller.TeacherController.TeacherFormController;
import bitcamp.myapp.controller.TeacherController.TeacherInsertController;
import bitcamp.myapp.controller.TeacherController.TeacherListController;
import bitcamp.myapp.controller.TeacherController.TeacherUpdateController;
import bitcamp.myapp.controller.TeacherController.TeacherViewController;
import bitcamp.myapp.controller.boardController.BoardDeleteController;
import bitcamp.myapp.controller.boardController.BoardFileDeleteController;
import bitcamp.myapp.controller.boardController.BoardFormController;
import bitcamp.myapp.controller.boardController.BoardInsertController;
import bitcamp.myapp.controller.boardController.BoardListController;
import bitcamp.myapp.controller.boardController.BoardUpdateController;
import bitcamp.myapp.controller.boardController.BoardViewController;
>>>>>>> 30b097aa2253aa35c03018e867fb53e4cd1a5960
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.dao.BoardFileDao;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.service.impl.DefaultBoardService;
import bitcamp.myapp.service.impl.DefaultStudentService;
import bitcamp.myapp.service.impl.DefaultTeacherService;
import bitcamp.util.BitcampSqlSessionFactory;
import bitcamp.util.Controller;
import bitcamp.util.DaoGenerator;
import bitcamp.util.RequestHandlerMapping;
import bitcamp.util.RequestMapping;
import bitcamp.util.TransactionManager;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

  List<Class<?>> controllerClasses = new ArrayList<>();

  List<Object> servicePool = new ArrayList<>();

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      InputStream mybatisConfigInputStream = Resources.getResourceAsStream(
          "bitcamp/myapp/config/mybatis-config.xml");
      SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
      BitcampSqlSessionFactory sqlSessionFactory = new BitcampSqlSessionFactory(
          builder.build(mybatisConfigInputStream));

      TransactionManager txManager = new TransactionManager(sqlSessionFactory);

      BoardDao boardDao = new DaoGenerator(sqlSessionFactory).getObject(BoardDao.class);
      MemberDao memberDao = new DaoGenerator(sqlSessionFactory).getObject(MemberDao.class);
      StudentDao studentDao = new DaoGenerator(sqlSessionFactory).getObject(StudentDao.class);
      TeacherDao teacherDao = new DaoGenerator(sqlSessionFactory).getObject(TeacherDao.class);
      BoardFileDao boardFileDao = new DaoGenerator(sqlSessionFactory).getObject(BoardFileDao.class);

<<<<<<< HEAD
      servicePool.add(new DefaultBoardService(txManager, boardDao, boardFileDao));
      servicePool.add(new DefaultStudentService(txManager, memberDao, studentDao));
      servicePool.add(new DefaultTeacherService(txManager, memberDao, teacherDao));
=======
      BoardService boardService = new DefaultBoardService(txManager, boardDao, boardFileDao);
      StudentService studentService = new DefaultStudentService(txManager, memberDao, studentDao);
      TeacherService teacherService = new DefaultTeacherService(txManager, memberDao, teacherDao);

      LoginFormController loginFormController = new LoginFormController();
      LoginController loginController = new LoginController(studentService, teacherService);
      LogoutController logoutController = new LogoutController();
      AuthFailController authFailController = new AuthFailController();

      BoardListController boardListController = new BoardListController(boardService);
      BoardFormController boardFormController = new BoardFormController();
      BoardInsertController boardInsertController = new BoardInsertController(boardService);
      BoardViewController boardViewController = new BoardViewController(boardService);
      BoardUpdateController boardUpdateController = new BoardUpdateController(boardService);
      BoardDeleteController boardDeleteController = new BoardDeleteController(boardService);
      BoardFileDeleteController boardFileDeleteController = new BoardFileDeleteController(boardService);

      TeacherListController teacherListController = new TeacherListController(teacherService);
      TeacherFormController teacherFormController = new TeacherFormController();
      TeacherInsertController teacherInsertController = new TeacherInsertController(teacherService);
      TeacherViewController teacherViewController = new TeacherViewController(teacherService);
      TeacherUpdateController teacherUpdateController = new TeacherUpdateController(teacherService);
      TeacherDeleteController teacherDeleteController = new TeacherDeleteController(teacherService);


>>>>>>> 30b097aa2253aa35c03018e867fb53e4cd1a5960

      // 서블릿 컨텍스트 보관소를 알아낸다.
      ServletContext ctx = sce.getServletContext();

      // 웹 애플리케이션 클래스가 배치되어 있는 폴더 알아내기
      // 배치 폴더에서 PageController 구현 클래스 찾기
      findPageController(new File(ctx.getRealPath("/WEB-INF/classes")), "");

      // 페이지 컨트롤러의 인스턴스 생성하기
      createPageControllers(ctx);

      ctx.setAttribute("/teacher/list", teacherListController);
      ctx.setAttribute("/teacher/form", teacherFormController);
      ctx.setAttribute("/teacher/insert", teacherInsertController);
      ctx.setAttribute("/teacher/view", teacherViewController);
      ctx.setAttribute("/teacher/update", teacherUpdateController);
      ctx.setAttribute("/teacher/delete", teacherDeleteController);

    } catch (Exception e) {
      System.out.println("웹 애플리케이션 자원을 준비하는 중에 오류 발생!");
      e.printStackTrace();
    }
  }

  private void findPageController(File dir, String packageName) throws Exception {
    File[] files = dir.listFiles(file -> file.isDirectory() || file.getName().endsWith(".class"));

    if (packageName.length() > 0) {
      packageName += ".";
    }

    for (File file : files) {
      String qName = packageName + file.getName(); // 패키지명 + 파일명  예) bitcamp.myapp.vo
      if (file.isDirectory()) {
        findPageController(file, qName);
      } else {
        Class<?> clazz = Class.forName(qName.replace(".class", ""));
        if (clazz.isInterface()) { // 인터페이스는 제외
          continue;
        }
        Controller anno = clazz.getAnnotation(Controller.class);
        if (anno != null) {
          controllerClasses.add(clazz);
        }
      }
    }
  }

  private void createPageControllers(ServletContext ctx) throws Exception {
    for (Class<?> c : controllerClasses) {
      Constructor<?> constructor = c.getConstructors()[0];
      Parameter[] params = constructor.getParameters();
      Object[] arguments = prepareArguments(params);
      Object controller = constructor.newInstance(arguments);

      // 페이지 컨트롤러에서 RequestMapping 애노테이션이 붙은 메서드를 찾아
      // ServletContext 보관소에 저장한다.
      Method[] methods = c.getDeclaredMethods();
      for (Method m : methods) {
        RequestMapping anno = m.getAnnotation(RequestMapping.class);
        if (anno == null) continue;
        ctx.setAttribute(anno.value(), new RequestHandlerMapping(controller, m));
        System.out.println(c.getName() + "." + m.getName() + "() 요청 핸들러 등록!");
      }

    }
  }

  private Object[] prepareArguments(Parameter[] params) {
    Object[] arguments = new Object[params.length];
    for (int i = 0; i < params.length; i++) {
      for (Object obj : servicePool) {
        if (params[i].getType().isInstance(obj)) {
          arguments[i] = obj;
          break;
        }
      }
    }
    return arguments;
  }


}






