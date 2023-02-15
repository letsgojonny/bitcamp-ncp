package bitcamp.myapp.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.StudentDao;
import bitcamp.myapp.vo.Student;

public class StudentDaoImpl implements StudentDao {

  SqlSessionFactory sqlSessionFactory;

  public StudentDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void insert(Student s) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      sqlSession.insert("StudentMapper.insert", s);
    }
  }

  @Override
  public List<Student> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("StudentMapper.findAll");
    }
  }

  @Override
  public Student findByNo(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("StudentMapper.findByNo", no);
    }
  }

  @Override
  public List<Student> findByKeyword(String keyword) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("StudentMapper.findByKeyword", keyword);
    }
  }

  @Override
  public int update(Student s) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.update("StudentMapper.update", s);
    }
  }

  @Override
  public int delete(int no) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.delete("StudentMapper.delete", no);
    }
  }
}























