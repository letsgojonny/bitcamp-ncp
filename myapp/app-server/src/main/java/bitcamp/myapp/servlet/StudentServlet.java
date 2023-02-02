package bitcamp.myapp.servlet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import com.google.gson.Gson;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;

public class StudentServlet {

  private StudentDao studentDao;

  public StudentServlet(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  private void onInsert(DataInputStream in, DataOutputStream out) throws Exception {
    // 클라이언트가 보낸 JSON 데이터를 읽어서 Student 객체로 만든다.
    Student b = new Gson().fromJson(in.readUTF(), Student.class);
    this.studentDao.insert(b);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onFindAll(DataInputStream in, DataOutputStream out) throws Exception {
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(this.studentDao.findAll()));
  }

  private void onFindByNo(DataInputStream in, DataOutputStream out) throws Exception {
    int studentNo = new Gson().fromJson(in.readUTF(), int.class);

    Student b = this.studentDao.findByNo(studentNo);
    if (b == null) {
      out.writeUTF("400");
      return;
    }
    out.writeUTF("200");
    out.writeUTF(new Gson().toJson(b));
  }

  private void onUpdate(DataInputStream in, DataOutputStream out) throws Exception{
    Student student = new Gson().fromJson(in.readUTF(), Student.class);

    Student old = this.studentDao.findByNo(student.getNo());
    if (old == null) {
      out.writeUTF("400");
      return;
    }
    this.studentDao.update(student);
    out.writeUTF("200");
    out.writeUTF("success");
  }

  private void onDelete(DataInputStream in, DataOutputStream out) throws Exception {
    Student student = new Gson().fromJson(in.readUTF(), Student.class);

    Student b = this.studentDao.findByNo(student.getNo());
    if (b == null) {
      out.writeUTF("400");
      return;
    }

    this.studentDao.delete(b);
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
        //        case "search": this.onSearch(in, out); break;
        default:
          System.out.println("잘못된 메뉴 번호 입니다.");
      }
    } catch (Exception e) {
      out.writeUTF("500");
    }
  }
}

