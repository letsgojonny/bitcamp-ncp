package hackathon2.backend.dao;

import java.sql.Date;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import hackathon2.backend.vo.Staff;

@Repository
public class StaffDao {

  private static final int SIZE = 1000;

  private int no;
  private int count;
  private Staff[] staffs = new Staff[SIZE];

  public int getCount() {
    return count;
  }

  public void insert(Staff staff) {
    staff.setNo(++no);
    staff.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.staffs[this.count++] = staff;
  }

  public Staff[] findAll() {
    return Arrays.copyOf(staffs, count);
  }

  public Staff findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.staffs[i].getNo() == no) {
        return this.staffs[i];
      }
    }
    return null;
  }

  public void update(Staff staff) {
    this.staffs[this.indexOf(staff)] = staff;
  }

  public void delete(Staff staff) {
    for (int i = this.indexOf(staff) + 1; i < this.count; i++) {
      this.staffs[i - 1] = this.staffs[i];
    }
    this.staffs[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  private int indexOf(Staff staff) {
    for (int i = 0; i < this.count; i++) {
      if (this.staffs[i].getNo() == staff.getNo()) {
        return i;
      }
    }
    return -1;
  }
}
