package bitcamp.bootapp.dao;

import java.sql.Date;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import bitcamp.bootapp.vo.Facility;

@Repository
public class FacilityDao {
  private static final int SIZE = 100;

  private int no;
  private int count;
  private Facility[] facilitys = new Facility[SIZE];

  public void insert(Facility facility) {
    facility.setNo(++no);
    facility.setInstalledDate(new Date(System.currentTimeMillis()).toString());
    this.facilitys[this.count++] = facility;
  }

  public Facility[] findAll() {
    return Arrays.copyOf(facilitys, count);
  }

  public Facility findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.facilitys[i].getNo() == no) {
        return this.facilitys[i];
      }
    }
    return null;
  }

  public void update(Facility facility) {
    this.facilitys[this.indexOf(facility)] = facility;
  }

  public void delete(Facility facility) {
    for (int i = this.indexOf(facility) + 1; i < this.count; i++) {
      this.facilitys[i - 1] = this.facilitys[i];
    }
    this.facilitys[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  private int indexOf(Facility facility) {
    for (int i = 0; i < this.count; i++) {
      if (this.facilitys[i].getNo() == facility.getNo()) {
        return i;
      }
    }
    return -1;
  }
}







