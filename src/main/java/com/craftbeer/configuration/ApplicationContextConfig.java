package com.craftbeer.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.craftbeer")
@PropertySource("classpath:configuration/application.properties")
@EnableScheduling
public class ApplicationContextConfig implements WebMvcConfigurer {

    @Value("${database.driverClassName}")
    private String dbDriverClassName;

    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.username}")
    private String dbUserName;

    @Value("${database.password}")
    private String dbPassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${entitymanager.packages.to.scan}")
    private String packagesToScan;

    @Value("${database.useSSL}")
    private String useSSL;

    @Value("${database.allowPublicKeyRetrieval}")
    private String allowPublicKeyRetrieval;

    @Value("${hibernate.current_session_context_class}")
    private String currentSessionContextClass;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "viewResolver")
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean(name = "dataResource")
    public DriverManagerDataSource dataResource(){
        DriverManagerDataSource dataResource = new DriverManagerDataSource();
        dataResource.setDriverClassName(dbDriverClassName);
        dataResource.setUrl(dbUrl);
        dataResource.setUsername(dbUserName);
        dataResource.setPassword(dbPassword);
        Properties properties = new Properties();
        properties.put("useSSL", useSSL);
        properties.put("allowPublicKeyRetrieval", allowPublicKeyRetrieval);
        dataResource.setConnectionProperties(properties);
        return dataResource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataResource());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("current_session_context_class", currentSessionContextClass);
        sessionFactoryBean.setPackagesToScan(packagesToScan);
        sessionFactoryBean.setHibernateProperties(properties);
        return sessionFactoryBean;
    }

    @Bean(name = "transactionManager")
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory().getObject());
        return hibernateTransactionManager;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}