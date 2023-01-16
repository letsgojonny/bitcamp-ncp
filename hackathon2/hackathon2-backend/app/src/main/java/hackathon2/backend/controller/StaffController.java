package hackathon2.backend.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.StaffDao;
import hackathon2.backend.vo.Staff;


@RestController
public class StaffController {

  @Autowired StaffDao staffDao;
  // Autowired는 인스턴스 필드에만 사용가능

  @PostMapping("/staffs")
  public Object addStaff(Staff staff) {

    this.staffDao.insert(staff);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }

  @GetMapping("/staffs")
  public Object getStaffs() {

    Staff[] staffs = this.staffDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", staffs);

    return contentMap;
  }

  @GetMapping("/staffs/{no}")
  public Object getStaff(@PathVariable int no) {

    Staff b = this.staffDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (b == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "직원이 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", b);
    }

    return contentMap;
  }


  @PutMapping("/staffs/{no}")
  public Object updateStaff(Staff staff) {

    Map<String,Object> contentMap = new HashMap<>();

    Staff old = this.staffDao.findByNo(staff.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "직원이 없습니다.");
      return contentMap;
    }

    staff.setCreatedDate(old.getCreatedDate());

    this.staffDao.update(staff);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/staffs/{no}")
  public Object deleteStaff(@PathVariable int no) {

    Staff m = this.staffDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "직원이 없습니다.");

    } else {
      this.staffDao.delete(m);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

  @GetMapping("/staffCnt")
  public Object getStaffCnt() {

    int cnt = this.staffDao.getCount();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", cnt);

    return contentMap;
  }
}
