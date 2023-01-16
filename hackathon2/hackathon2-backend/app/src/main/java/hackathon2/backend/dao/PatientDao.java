package hackathon2.backend.dao;

import java.sql.Date;
import java.util.Arrays;
import org.springframework.stereotype.Repository;
import hackathon2.backend.vo.Patient;

@Repository
public class PatientDao {
  private static final int SIZE = 100;


  private int no;
  private int count;


  public int getCount() {
    return count;
  }

  private Patient[] patients = new Patient[SIZE];

  public void insert(Patient patient) {
    patient.setNo(++no);
    patient.setCreatedDate(new Date(System.currentTimeMillis()).toString());
    this.patients[this.count++] = patient;
  }

  public Patient[] findAll() {
    return Arrays.copyOf(patients, count);
  }

  public Patient findByNo(int no) {
    for (int i = 0; i < this.count; i++) {
      if (this.patients[i].getNo() == no) {
        return this.patients[i];
      }
    }
    return null;
  }

  public void update(Patient patient) {
    this.patients[this.indexOf(patient)] = patient;
  }

  public void delete(Patient patient) {
    for (int i = this.indexOf(patient) + 1; i < this.count; i++) {
      this.patients[i - 1] = this.patients[i];
    }
    this.patients[--this.count] = null; // 레퍼런스 카운트를 줄인다.
  }

  private int indexOf(Patient p) {
    for (int i = 0; i < this.count; i++) {
      if (this.patients[i].getNo() == p.getNo()) {
        return i;
      }
    }
    return -1;
  }

  public Patient findByName(String searches) {
    for (int i = 0; i < this.count; i++) {
      if (this.patients[i].getName().equals(searches)) {
        return this.patients[i];
      }
    }
    return null;
  }


}







