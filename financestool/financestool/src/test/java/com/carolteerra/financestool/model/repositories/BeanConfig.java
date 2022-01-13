package com.carolteerra.financestool.model.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class BeanConfig {
    @Autowired
    EntityManagerFactory emf;

    @Autowired
    @Bean
    public SessionFactory sessionFactory() {
        return emf.unwrap(SessionFactory.class);
    }
}
