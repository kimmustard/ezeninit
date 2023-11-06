package com.myweb.www.config;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = { "com.myweb.www.repository" })
@ComponentScan(basePackages = {"com.myweb.www.service","com.myweb.www.handler","com.myweb.www.exception"})
@EnableAspectJAutoProxy	//aspect 프록시모드
@EnableTransactionManagement	//트랜잭션 사용
@EnableScheduling	//스케줄러
public class RootConfig {
	// DB 설정부분
	// 전과 달라진 부분 Logf4jdbc-log4j2 사용
	// Hikari-CP 사용


	@Autowired
	ApplicationContext applicationContext;

//	@Autowired
//	public RootConfig(ApplicationContext applicationContext) {
//		this.applicationContext = applicationContext;
//	}

	@Bean
	public DataSource dataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		// log4jdbc - log4j2의 드라이버 클래스 url을 사용
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springdb");
		hikariConfig.setUsername("springUser");
		hikariConfig.setPassword("mysql");

		hikariConfig.setMaximumPoolSize(5); // 최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); // 최소 유휴 커넥션 개수 (반드시 같은 값으로 설정)

		hikariConfig.setConnectionTestQuery("SELECT now()"); // 설정 후 첫연결 테스트 구문입력
		hikariConfig.setPoolName("springHikariCP"); // 커넥션 풀 이름지정

		// RootConfig 추가 설정
		// 캐시(cache) 사용여부 설정 true
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		// mySql 드라이버가 한 연결당 cachePrepStatment의 수에 관한 설정 default값 = 25 보통 25~500 사이 권장
		hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
		// mySql connection당 캐싱할 perparedStatement의 개수 지정 옵션 default값 = 256 보통 2408개 까지 가능
		hikariConfig.addDataSourceProperty("dataSource.perpStmtCacheSqlLimit", "true"); // true 기본값 설정
		// mySql 서버에서 최신 이슈가 있을때, 지원받는 설정 server-side 지원 설정 true
		hikariConfig.addDataSourceProperty("dataSource.useServerPerpStmts", "true");

		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		return hikariDataSource;
	}

	@Bean
	public SqlSessionFactory sqlSesionFactory() throws Exception {
	
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSource());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/MybatisConfig.xml"));
		
		return (SqlSessionFactory) sqlFactoryBean.getObject();
	}
	
	// 트랜잭션 매니저 빈 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	
}
