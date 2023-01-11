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
import bitcamp.bootapp.dao.MemberDao;
import bitcamp.bootapp.vo.Member;

@CrossOrigin(origins = {"http://192.168.0.36:5500", "http://localhost:5500", "http://*"})
@RestController
public class MemberController {

  MemberDao memberDao = new MemberDao();

  @PostMapping("/members")
  public Object addMember(
      String name,  // ..&name=xxxx&..
      String tel,   // ..&tel=xxxx&..
      String postno,
      String basicaddress,
      boolean working,  // ..&working=xxx&.. => "true"=true/"false"=false, 파라미터 없으면 false,
      // "on"=true/"off"=false, "1"=true/"0"=false, 그 밖의 문자열은 변환 오류 발생!!
      char gender,      // ..&gender=M&.. => 문자 1개의 문자열 변환, null 또는 그 밖의 문자열은 변환 오류 발생!
      byte level        // ..&level=1&.. => Byte.parseByte("1") => 1, null 또는 byte 범위를 초과하는 숫자는 변환 오류 발생!
      ) {
    System.out.printf("%s,%s,%s,%s,%b,%c,%d\n",
        name,tel,postno,basicaddress,working,gender,level);

    Member m = new Member();
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

  @PutMapping("/members/{no}")
  public Object updateMember(
      //      @PathVariable int no,
      Member member) {

    System.out.printf("%d,%s,%s,%s,%s,%b,%c,%d\n",
        member.getNo(),member.getName(),member.getTel(),member.getPostNo(),member.getBasicAddress(),
        member.isWorking(),member.getGender(), member.getLevel());

    Map<String,Object> contentMap = new HashMap<>();


    Member old = this.memberDao.findByNo(member.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "멤버를 찾을 수 없습니다.");
      return contentMap;
    }

    member.setCreatedDate(old.getCreatedDate());

    this.memberDao.update(member);

    // 응답 결과를 담을 맵 객체 준비
    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/members/{memberNo}")
  public Object deleteMember(
      // 낱개로 받을 때는 @PathVariable 애노테이션을 생략하면 안된다.
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
