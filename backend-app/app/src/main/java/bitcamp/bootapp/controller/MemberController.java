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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

@CrossOrigin(origins = {"http://192.168.0.36:5500", "http://localhost:5500"})
@RestController
public class MemberController {

  MemberDao memberDao = new MemberDao();

  @PostMapping("/members")
  public Object addMember(
      @RequestParam(required = false) int no,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postno,
      @RequestParam(required = false) String basicaddress,
      @RequestParam(required = false) boolean working,
      @RequestParam(required = false) char gender,
      @RequestParam(required = false) byte level) {

    Member m = new Member();
    m.setNo(no);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postno);
    m.setBasicAddress(basicaddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setCreatedDate(new Date(System.currentTimeMillis()).toString());

    this.memberDao.insert(m);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/members")
  public Object getMembers() {

    Member[] members = this.memberDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", members);

    return contentMap;
  }

  @GetMapping("/members/{memberNo}")
  public Object getMember(@PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "해당 번호의 멤버가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", m);
    }

    return contentMap;
  }

  @PutMapping("/members/{memberNo}")
  public Object updateMember(
      @PathVariable int memberNo,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String tel,
      @RequestParam(required = false) String postno,
      @RequestParam(required = false) String basicaddress,
      @RequestParam(required = false) boolean working,
      @RequestParam(required = false) char gender,
      @RequestParam(required = false) byte level) {

    Map<String,Object> contentMap = new HashMap<>();

    Member old = this.memberDao.findByNo(memberNo);
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "멤버를 찾을 수 없습니다.");
      return contentMap;
    }

    Member m = new Member();
    m.setNo(memberNo);
    m.setName(name);
    m.setTel(tel);
    m.setPostNo(postno);
    m.setBasicAddress(basicaddress);
    m.setWorking(working);
    m.setGender(gender);
    m.setLevel(level);
    m.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(m);

    // 응답 결과를 담을 맵 객체 준비
    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
      @PathVariable int memberNo) {

    Member m = this.memberDao.findByNo(memberNo);

    // 응답 결과를 담을 맵 객체 준비
    Map<String,Object> contentMap = new HashMap<>();

    if (m == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "멤버를 찾을 수 없습니다.");

    } else {
      this.memberDao.delete(m);
      contentMap.put("status", "success");
    }

    return contentMap;
  }
}
