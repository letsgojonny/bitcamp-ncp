package bitcamp.myapp.service;

import java.util.List;
import bitcamp.myapp.vo.Member;

public interface MemberService {
  void add(Member member);
  List<Member> list(String keyword);
  Member get(int no);
  Member get(String email, String password);
  Member get(String email);
  void update(Member member);
  void delete(int no);
}





