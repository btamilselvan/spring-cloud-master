package com.success.configs;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("custom")
public class DatasourceConfig {

  @Value("${app.datasource.primary.username}")
  private String username;

  @Value("${app.datasource.primary.password}")
  private String password;

  @Value("${app.datasource.primary.jdbcUrl}")
  private String url;

  @Value("${app.datasource.replica.username}")
  private String replicaUsername;

  @Value("${app.datasource.replica.password}")
  private String replicaPassword;

  @Value("${app.datasource.replica.jdbcUrl}")
  private String replicaUrl;
  // either this way.. or the other way
  /*@Bean
  @Primary
  protected DataSource primaryDatasource() {
    DataSourceBuilder<?> builder = DataSourceBuilder.create();
    DataSource ds = builder.password(password).url(url).username(username).build();
    return ds;
  }

  @Bean
  @Qualifier("replica")
  protected DataSource replicaDatasource() {
    DataSourceBuilder<?> builder = DataSourceBuilder.create();
    DataSource ds = builder.password(replicaPassword).url(replicaUrl).username(replicaUsername).build();
    return ds;
  }*/

  /*@Bean
  @ConfigurationProperties(prefix = "app.datasource.primary")
  @Primary
  protected DataSource primaryDatasource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  @ConfigurationProperties(prefix = "app.datasource.replica")
  protected DataSource replicaDatasource() {
    return DataSourceBuilder.create().build();
  }*/

  @Bean
  @ConfigurationProperties(prefix = "app.datasource.primary")
  protected HikariConfig primary() {
    return new HikariConfig();
  }

  @Bean
  @ConfigurationProperties(prefix = "app.datasource.replica")
  protected HikariConfig replica() {
    return new HikariConfig();
  }

  @Bean
  protected DataSource routingDataSource() {
    return new RoutingDatasource(new HikariDataSource(primary()), new HikariDataSource(replica()));
  }

  /**
   * the method name should be entityManagerFactory - spring will search for the bean named
   * "entityManagerFactory"
   *
   * @return
   */
  @Bean
  protected LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(routingDataSource());
    em.setPackagesToScan("com.success");
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setShowSql(true);
    //    Properties jpaProperties = new Properties();
    //    jpaProperties.put("hibernate.show_sql", "true");
    //    em.setJpaProperties(jpaProperties);
    em.setJpaVendorAdapter(adapter);
    return em;
  }

  @Bean
  @Qualifier("jpaTxnManager")
  protected PlatformTransactionManager jpaPlatformEntityManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  /**
   * the method name should be transactionManager - spring will search for the bean named
   * "transactionManager"
   *
   * @param jpaTxnManager
   * @return
   */
  @Bean
  @Primary
  protected PlatformTransactionManager transactionManager(
      @Qualifier("jpaTxnManager") PlatformTransactionManager jpaTxnManager) {
    return new ReplicaAwareTxnManager(jpaTxnManager);
  }
}
