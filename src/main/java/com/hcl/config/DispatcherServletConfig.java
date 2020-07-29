package com.hcl.config;
import com.hcl.dao.*;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
@ComponentScan("com.hcl")
@EnableWebMvc
public class DispatcherServletConfig {//acts as a front controller in mvc architecture
	
	@Bean
	public InternalResourceViewResolver getViewResolver() {
		
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	        viewResolver.setPrefix("WEB-INF/views/");//specifying where my .jsp are present
	        viewResolver.setSuffix(".jsp");//specifying to look for .jsp extension files in the path 
	        return viewResolver;
	}
	@Bean //bean annotations works like bean xml tags 
	   public UserDao userDao(){
	       UserDao userdao = new UserDao();
	       userdao.setJdbcTemplate(jdbcTemplate());
	       return userdao;
	   }
	@Bean
	   public JdbcTemplate jdbcTemplate() {//JdbcTemplate is used to execute the different types of SQL statements
		//like update, insert, delete tables etc..
	       JdbcTemplate jdbcTemplate = new JdbcTemplate();
	       jdbcTemplate.setDataSource(dataSource());
	       return jdbcTemplate;
	   }
	   @Bean
	   public DataSource dataSource() {
	       DriverManagerDataSource dataSource = new DriverManagerDataSource();
	       //I am using MySQL database 
	       dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	       dataSource.setUrl("jdbc:mysql://localhost:3306/bank");//data base name is bank
	       dataSource.setUsername("root");//my mysql username
	       dataSource.setPassword("SunilKumar@123");//my mysql password
	       
	      
	       return dataSource;
	       
	   }
	
}
