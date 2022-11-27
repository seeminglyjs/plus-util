package com.source.plusutil.config;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.slf4j.Slf4j;

//@Configuration
@Slf4j
//@EnableJpaAuditing
//[******************************미사용************************************]
public class DbConfig {
	
//	@Autowired
//	PropertiesConfig propertiesConfig;
//	
//    @Bean    
//    public PasswordEncoder passwordEncoder() {
//    	return PasswordEncoderFactories.createDelegatingPasswordEncoder(); 
//    	//return NoOpPasswordEncoder.getInstance();   
//    	} 
//    	//    public DataSource dataSource() {
//    	//        log.info("dataSource setup...");
//    	//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//    	//        EmbeddedDatabase db = builder
//    	//  .setType(EmbeddedDatabaseType.H2)
//    	//                .build();
//    	//        return db;
//    	//    } 	
//    	@Bean    
//    	public DataSource dataSource() {
//    		log.info("dataSource setup...");        
//    		DataSource dataSource = DataSourceBuilder.create()                
//    				.url(propertiesConfig.getDatasourceUrl())                
//    				.username(propertiesConfig.getDatasourceUsername())                
//    				.password(propertiesConfig.getDatasourcePassword())                
//    				.build();       
//    		return dataSource;    
//    		}
    }