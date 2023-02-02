package bitcamp.myapp.dao;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import bitcamp.myapp.vo.Teacher;

public class NetworkTeacherDao implements TeacherDao {

  List<Teacher> list;
  int lastNo;
  DataInputStream in;
  DataOutputStream out;

  public NetworkTeacherDao(DataInputStream in, DataOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void insert(Teacher t) {
    fetch("teacher", "insert", t);
  }

  @Override
  public Teacher[] findAll() {
    return new Gson().fromJson(fetch("teacher", "findAll"), Teacher[].class);
  }

  @Override
  public Teacher findByNo(int no) {
    return new Gson().fromJson(fetch("teacher", "findAll"), Teacher.class);
  }

  @Override
  public void update(Teacher b) {
    fetch("teacher", "update", b);
  }

  @Override
  public boolean delete (Teacher b) {
    fetch("teacher", "delete", b);
    return true;
  }

  public void save(String filename) {
    try (FileWriter out = new FileWriter(filename)) {

      out.write(new Gson().toJson(list));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void load(String filename) {
    if (list.size() > 0) { // 중복 로딩 방지!
      return;
    }

    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      // 1) JSON 데이터를 어떤 타입의 객체로 변환할 것인지 그 타입 정보를 준비한다.
      TypeToken<List<Teacher>> collectionType = new TypeToken<>() {};

      // 2) 입력 스트림에서 JSON 데이터를 읽고, 지정한 타입의 객체로 변환하여 리턴한다.
      list = new Gson().fromJson(in, collectionType);

      if (list.size() > 0) {
        lastNo = list.get(list.size() - 1).getNo();
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public String fetch(String dataName, String action, Object... data) throws DaoException {
    try {
      out.writeUTF(dataName);
      out.writeUTF(action);

      if (data.length > 0) {
        out.writeUTF(new Gson().toJson(data[0]));
      }

      // 서버의 응답
      String status = in.readUTF();
      if (status.equals("400")) {
        throw new DaoException("client 요청 오류");
      } else if (status.equals("500")) {
        throw new DaoException("server 실행 오류");
      }
      return in.readUTF();

    } catch (DaoException e) {
      throw e;
    } catch (Exception e) {
      throw new DaoException("오류 발생!", e);
    }
  }
}























