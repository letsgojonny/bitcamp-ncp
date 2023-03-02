package bitcamp.myapp.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

//@Configuration

// Spring IoC 컨테이너가 자동 생성할 클래스를 찾을 수 있도록 패키지를 지정한다.
@ComponentScan("bitcamp.myapp")

// JDBC 설정 정보를 담고 있는 .properties 파일을 로딩한다.
@PropertySource("classpath:/bitcamp/myapp/config/jdbc.properties")

// Mybatis-Spring 라이브러리에 있는 클래스를 사용하여 DAO 인터페이스의 구현체를 자동 생성하기
@MapperScan("bitcamp.myapp.dao")
public class AppConfig {

  // 시스템 property 값 가져오기
  @Autowired Environment env;

  // DB 커넥션풀 객체 준비
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(env.getProperty("jdbc.driver"));
    ds.setUrl(env.getProperty("jdbc.url"));
    ds.setUsername(env.getProperty("jdbc.username"));
    ds.setPassword(env.getProperty("jdbc.password"));
    return ds;
  }

  //트랜잭션 관리자 준비
  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) throws Exception {
    System.out.println("PlatformTransactionManager 객체 생성! ");
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx) throws Exception {
    System.out.println("SqlSessionFactory 객체 생성!");
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("bitcamp.myapp.vo");
    factoryBean.setMapperLocations(appCtx.getResources("classpath*:bitcamp/myapp/mapper/*Mapper.xml"));
    return factoryBean.getObject();
  }

  // Servlet3.0의 멀티파트 요청 데이터를 처리하려면
  // 1) 서블릿에 멀티파트 파일 처리에 관한 설정 정보를 담은 MultipartConfigElement를 등록해야 한다
  // 2) 스프링 WebMVC에는 Servlet3.0 API를 이용해 멀티파트 데이터를 처리한 후에
  //    페이지 컨트롤러에게 그 값들을 전달해주는 StandardServletMultipartResolver를 등록해야 한다
  // 주의!
  //    이때 MultipartResolver 구현체 bean 이름은 "multipartResolver"여야 한다.
  @Bean
  public MultipartResolver multipartResolver() {
    return new StandardServletMultipartResolver();
  }

  // Apache commons-fileupload 라이브러리로 멀티파트 요청 데이터를 처리하려면
  // 1) 프로젝트에 commons-fileupload 라이브러리를 별도로 추가해야 한다.
  // 2) 스프링 WebMVC에는 이 라이브러리를 이용해 멀티파트 데이터를 처리한 후에
  //    페이지 컨트롤러에게 그 값들을 전달해주는 CommonsMultipartResolver를 등록해야 한다
  // 주의!
  //    이때 MultipartResolver 구현체 bean 이름은 "multipartResolver"여야 한다.
  //    @Bean
  //    public MultipartResolver multipartResolver() {
  //      return new CommonsMultipartResolver();
  //    }

}








