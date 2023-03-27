package bitcamp.myapp.vo;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

@Data
public class Member {
  private int no;
  private String name;
  private String tel;
  private String email;
  private String id;
  private String password;
  private String profilePhoto;
  private String nickname;
  private String postNo;
  private String basicAddress;
  private String detailAddress;
  private String birth;
  private String statusMessage;
  private String authority;

  //Jackson 라이브러리가 Date 타입 값을 JSON 문자열로 변환할 때 사용할 규칙을 설정한다.
  @JsonFormat(
      shape = Shape.STRING,
      pattern = "yyyy-MM-dd")
  private Date createdDate;
}
