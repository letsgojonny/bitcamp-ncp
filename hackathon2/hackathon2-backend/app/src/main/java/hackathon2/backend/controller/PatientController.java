package hackathon2.backend.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import hackathon2.backend.dao.PatientDao;
import hackathon2.backend.vo.Patient;

@RestController
public class PatientController {

  PatientDao patientDao = new PatientDao();

  @PostMapping("/patients")
  public Object addPatient(Patient patient) {

    this.patientDao.insert(patient);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }


  @GetMapping("/patients")
  public Object getPatients() {

    Patient[] patients = this.patientDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", patients);

    return contentMap;
  }

  @GetMapping("/patientss/{searches}")
  public Object getPatientName(@PathVariable String searches) {

    Patient p = this.patientDao.findByName(searches);

    Map<String,Object> contentMap = new HashMap<>();

    if (p == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "환자가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", p);
    }

    return contentMap;
  }

  @GetMapping("/patients/{no}")
  public Object getPatient(@PathVariable int no) {

    Patient p = this.patientDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (p == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "환자가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", p);
    }

    return contentMap;
  }

  @PutMapping("/patients/{no}")
  public Object updatePatient(Patient patient) {

    Map<String,Object> contentMap = new HashMap<>();

    Patient old = this.patientDao.findByNo(patient.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "환자가 없습니다.");
      return contentMap;
    }

    patient.setCreatedDate(old.getCreatedDate());

    this.patientDao.update(patient);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/patients/{no}")
  public Object deletePatient(@PathVariable int no) {

    Patient p = this.patientDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (p == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "환자가 없습니다.");

    } else {
      this.patientDao.delete(p);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

  @GetMapping("/patientCnt")
  public Object getPatientCnt() {

    int cnt = this.patientDao.getCount();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", cnt);

    return contentMap;
  }
}
