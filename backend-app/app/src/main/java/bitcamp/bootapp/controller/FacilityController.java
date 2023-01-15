package bitcamp.bootapp.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.bootapp.dao.FacilityDao;
import bitcamp.bootapp.vo.Facility;

@RestController
public class FacilityController {

  FacilityDao facilityDao = new FacilityDao();

  public FacilityController(FacilityDao facilityDao) {
    this.facilityDao = facilityDao;
  }



  @PostMapping("/facilities")
  public Object addFacility(Facility facility) {

    System.out.println("post done");
    this.facilityDao.insert(facility);

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");

    return contentMap;
  }



  @GetMapping("/facilities")
  public Object getFacilities() {

    Facility[] facilities = this.facilityDao.findAll();

    Map<String,Object> contentMap = new HashMap<>();
    contentMap.put("status", "success");
    contentMap.put("data", facilities);

    return contentMap;
  }



  @GetMapping("/facilities/{no}")
  public Object getFacility(@PathVariable int no) {

    Facility p = this.facilityDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (p == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "장비가 없습니다.");
    } else {
      contentMap.put("status", "success");
      contentMap.put("data", p);
    }

    return contentMap;
  }

  @PutMapping("/facilities/{no}")
  public Object updateFacility(Facility facility) {

    Map<String,Object> contentMap = new HashMap<>();

    Facility old = this.facilityDao.findByNo(facility.getNo());
    if (old == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "장비가 없습니다.");
      return contentMap;
    }

    facility.setInstalledDate(old.getInstalledDate());

    this.facilityDao.update(facility);

    contentMap.put("status", "success");

    return contentMap;
  }

  @DeleteMapping("/facilities/{no}")
  public Object deleteFacility(@PathVariable int no) {

    Facility p = this.facilityDao.findByNo(no);

    Map<String,Object> contentMap = new HashMap<>();

    if (p == null) {
      contentMap.put("status", "failure");
      contentMap.put("data", "장비가 없습니다.");

    } else {
      this.facilityDao.delete(p);
      contentMap.put("status", "success");
    }

    return contentMap;
  }

}
