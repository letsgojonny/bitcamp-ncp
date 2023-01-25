
package bitcamp.myapp.dao;

import java.sql.Date;
import bitcamp.myapp.util.Iterator;
import bitcamp.myapp.util.List;
import bitcamp.myapp.vo.Teacher;

public class TeacherDao {

  List list;

  int lastNo;

  public TeacherDao(List list) {
    this.list = list;
  }

  public void insert(Teacher teacher) {
    teacher.setNo(++lastNo);
    teacher.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    list.add(teacher);
  }

  public Teacher[] findAll() {
    Teacher[] teachers = new Teacher[list.size()];
    Iterator i = list.iterator();
    int index = 0;
    while (i.hasNext()) {
      teachers[index++] = (Teacher) i.next();
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

    return (Teacher) list.get(index);
  }

  public void update(Teacher t) {
    int index = list.indexOf(t);
    list.set(index, t);
  }
  public boolean delete(Teacher t) {
    return list.remove(t);
  }
}







