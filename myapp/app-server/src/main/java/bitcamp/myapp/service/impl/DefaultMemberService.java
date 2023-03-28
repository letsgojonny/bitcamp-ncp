package bitcamp.myapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bitcamp.myapp.dao.MemberDao;
import bitcamp.myapp.service.MemberService;
import bitcamp.myapp.vo.Member;

@Service
public class DefaultMemberService implements MemberService {

  @Autowired private MemberDao memberDao;

  @Transactional
  @Override
  public void add(Member member) {
    memberDao.insert(member);
  }

  @Override
  public List<Member> list(String keyword) {
    return memberDao.findAll(keyword);
  }

  @Override
  public Member get(int no) {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String email, String password) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("email", email);
    paramMap.put("password", password);

    return memberDao.findByEmailAndPassword(paramMap);
  }

  @Override
  public Member get(String email) {
    return memberDao.findByEmail(email);
  }

  @Transactional
  @Override
  public void update(Member member) {
    if (memberDao.update(member) == 1) {
    } else {
      throw new RuntimeException("회원이 존재하지 않습니다.");
    }
  }

  @Transactional
  @Override
  public void delete(int no) {
    if (memberDao.delete(no) == 1) {
    } else {
      throw new RuntimeException("회원이 존재하지 않습니다.");
    }
  }
}





