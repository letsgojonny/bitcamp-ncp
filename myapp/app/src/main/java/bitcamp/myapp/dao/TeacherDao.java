package bitcamp.myapp.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import bitcamp.myapp.vo.Teacher;

public class TeacherDao {

  List<Teacher> list;
  int lastNo;

  public TeacherDao(List<Teacher> list) {
    this.list = list;
  }

  public void insert(Teacher t) {
    t.setNo(++lastNo);
    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    list.add(t);
  }

  public Teacher[] findAll() {
    Teacher[] teachers = new Teacher[list.size()];
    Iterator<Teacher> i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      teachers[index++] = i.next();
    }
    return teachers;
  }

  public Teacher findByNo(int no) {
    Teacher t = new Teacher();
    t.setNo(no);

    int index = list.indexOf(t);
    if (index == -1) {
      return null;
    }

    return list.get(index);
  }

  public void update(Teacher t) {
    int index = list.indexOf(t);
    list.set(index, t);
  }

  public boolean delete(Teacher t) {
    return list.remove(t);
  }

  public void save(String filename) {
    try (FileWriter out = new FileWriter(filename)) {
      for (Teacher t : list) {
        out.write(String.format("%d,%s,%s,%s,%d,%s,%s,%d,%s\n",
            t.getNo(),
            t.getName(),
            t.getTel(),
            t.getEmail(),
            t.getDegree(),
            t.getSchool(),
            t.getMajor(),
            t.getWage(),
            t.getCreatedDate()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void load(String filename) {
    if (list.size() > 0) {
      return;
    }
    try (Scanner in = new Scanner(new FileReader(filename))) {
      while (true) {
        try {
          String[] values = in.nextLine().split(",");
          Teacher t = new Teacher();
          t.setNo(Integer.parseInt(values[0]));
          t.setName(values[1]);
          t.setTel(values[2]);
          t.setEmail(values[3]);
          t.setDegree(Integer.parseInt(values[4]));
          t.setSchool(values[5]);
          t.setMajor(values[6]);
          t.setWage(Integer.parseInt(values[7]));
          t.setCreatedDate(values[8]);
          list.add(t);
        } catch (Exception e) {
          break;
        }
      }
      if (list.size() > 0) {
        lastNo = list.get(list.size() - 1).getNo();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}







