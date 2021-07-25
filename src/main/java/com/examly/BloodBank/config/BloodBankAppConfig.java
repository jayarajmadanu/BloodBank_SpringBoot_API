package com.examly.BloodBank.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.examly"})
public class BloodBankAppConfig implements WebMvcConfigurer{
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourse());
		return jdbcTemplate;
	}
	
	@Bean
	public DataSource dataSourse() {
		DriverManagerDataSource dataSourse = new DriverManagerDataSource();
		dataSourse.setUsername("root");
		dataSourse.setPassword("AaBb33@@");
		dataSourse.setUrl("jdbc:mysql://localhost:3306/virtusa?useSSL=false");
		dataSourse.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return dataSourse;
	}

}
