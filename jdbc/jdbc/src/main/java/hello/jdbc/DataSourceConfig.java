package hello.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.h2")
//    public HikariConfig h2Config() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    //@Profile("h2")
//    public DataSource H2DataSource() {
//        return new HikariDataSource(h2Config());
//    }
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.mysql")
//    public HikariConfig MySQLConfig() {
//        return new HikariConfig();
//    }
//
//    @Bean
//    public DataSource MySQLDataSource() {
//        return new HikariDataSource(MySQLConfig());
//    }
}
