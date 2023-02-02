package bitcamp.myapp.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import bitcamp.myapp.dao.TeacherDao;
import bitcamp.myapp.vo.Teacher;

public class TeacherServlet {

  private TeacherDao teacherDao;

  public TeacherServlet(TeacherDao teacherDao) {
    this.teacherDao = teacherDao;
  }

  private void onInsert(DataInputStream in, DataOutputStream out) throws Exception {
    // 클라이언트가 보낸 JSON 데이터를 읽어서 Teacher 객체로 만든다.
    Teacher t = new Gson().fromJson(in.readUTF(), Teacher.class);
    this.teacherDao.insert(t);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onFindAll(DataInputStream in, DataOutputStream out) throws Exception {
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(this.teacherDao.findAll()));
  }

  private void onFindByNo(DataInputStream in, DataOutputStream out) throws Exception {
    int teacherNo = new Gson().fromJson(in.readUTF(), int.class);

    Teacher t = this.teacherDao.findByNo(teacherNo);
    if (t == null) {
      out.writeUTF("400");
      return;
    }
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(t));
  }

  private void onUpdate(DataInputStream in, DataOutputStream out) throws Exception{
    Teacher teacher = new Gson().fromJson(in.readUTF(), Teacher.class);

    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      out.writeUTF("400");
      return;
    }
    this.teacherDao.update(teacher);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onDelete(DataInputStream in, DataOutputStream out) throws Exception {
    Teacher teacher = new Gson().fromJson(in.readUTF(), Teacher.class);

    Teacher t = this.teacherDao.findByNo(teacher.getNo());
    if (t == null) {
      out.writeUTF("400");
      return;
    }

    this.teacherDao.delete(t);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  public void service(DataInputStream in, DataOutputStream out) throws Exception {
    try {
      // 클라이언트가 요구하는 액션을 읽는다.
      String action = in.readUTF();

      switch (action) {
        case "insert": this.onInsert(in, out); break;
        case "findAll": this.onFindAll(in, out); break;
        case "findByNo": this.onFindByNo(in, out); break;
        case "update": this.onUpdate(in, out); break;
        case "delete": this.onDelete(in, out); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    } catch (Exception e) {
      out.writeUTF("500");
    }
  }
}
