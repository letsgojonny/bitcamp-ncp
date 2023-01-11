package bitcamp.bootapp.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.TeacherDao;
import bitcamp.bootapp.vo.Teacher;

@CrossOrigin(origins = {"http://192.168.0.36:5500", "http://localhost:5500"})
@RestController
public class TeacherController {

  TeacherDao teacherDao = new TeacherDao();

  @PostMapping("/teachers")
  public Object addTeacher(
      String name,
      String tel,
      String email,
      String finalEducation,
      String university,
      String major,
      byte pay
      ) {
    System.out.printf("%s,%s,%s,%s,%s,%s,%d\n",
        name,tel,email,finalEducation,university,major,pay);

    Teacher t = new Teacher();
    t.setName(name);
    t.setTel(tel);
    t.setEmail(email);
    t.setFinalEducation(finalEducation);
    t.setUniversity(university);
    t.setMajor(major);
    t.setPay(pay);

    t.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.teacherDao.insert(t);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    return contentMap;
  }

  @GetMapping("/teachers")
  public Object getTeachers() {

    Teacher[] teachers = this.teacherDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", teachers);

    System.out.println(teachers);
    return contentMap;
  }


  @GetMapping("/teachers/{teacherNo}")
  public Object getTeacher(@PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 강사가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", t);
    }

    return contentMap;
  }

  @PutMapping("/teachers/{no}")
  public Object updateTeacher(
      //      @PathVariable int no,
      Teacher teacher) {

    System.out.printf("%s,%s,%s,%s,%s,%s,%d\n",
        teacher.getName(),teacher.getTel(),teacher.getEmail(),teacher.getFinalEducation(),
        teacher.getUniversity(),teacher.getMajor(),teacher.getPay());

    Map<String,Object> contentMap = new HashMap<>();


    Teacher old = this.teacherDao.findByNo(teacher.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "강사를 찾을 수 없습니다.");
      return contentMap;
    }

    teacher.setCreatedDate(old.getCreatedDate());

    this.teacherDao.update(teacher);

    // 응답 결과를 담을 맵 객체 준비
    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/teachers/{teacherNo}")
  public Object deleteTeacher(
      // 낱개로 받을 때는 @PathVariable 애노테이션을 생략하면 안된다.
      @PathVariable int teacherNo) {

    Teacher t = this.teacherDao.findByNo(teacherNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (t == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "멤버를 찾을 수 없습니다.");

    } else {
      this.teacherDao.delete(t);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

}
