package bitcamp.myapp.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import bitcamp.myapp.service.ObjectStorageService;
import bitcamp.myapp.service.TeacherService;
import bitcamp.myapp.vo.Teacher;
import bitcamp.util.RestResult;
import bitcamp.util.RestStatus;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

  Logger log = LogManager.getLogger(getClass());

  {
    log.trace("TeacherController 생성됨!");
  }

  @Autowired private TeacherService teacherService;
  @Autowired private ObjectStorageService objectStorageService;
  private String bucketName = "bitcamp-bucket08-member-photo";

  @PostMapping
  public Object insert(Teacher teacher, MultipartFile file) {
    String filename = objectStorageService.uploadFile(bucketName, "", file);
    if (filename != null) {
      teacher.setPhoto(filename);
    }

    teacherService.add(teacher);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @GetMapping
  public Object list() {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(teacherService.list());
  }

  @GetMapping("{no}")
  public Object view(@PathVariable int no) {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(teacherService.get(no));
  }

  @PutMapping("{no}")
  public Object update(
      @PathVariable int no,
      Teacher teacher,
      MultipartFile file) {

    String filename = objectStorageService.uploadFile(bucketName, "", file);
    if (filename != null) {
      teacher.setPhoto(filename);
    }

    log.debug(teacher);

    teacher.setNo(no);
    teacherService.update(teacher);

    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @DeleteMapping("{no}")
  public Object delete(@PathVariable int no) {
    teacherService.delete(no);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

}
