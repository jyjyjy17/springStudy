package hello.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@ActiveProfiles("h2")
class DataSourceConfigTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    void h2DataSource() {
        assertThat(dataSource).isNotNull();
        assertThat(dataSource).isInstanceOf(HikariDataSource.class);

        HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
        assertThat(hikariDataSource.getMaximumPoolSize()).isEqualTo(10);
        assertThat(hikariDataSource.getConnectionTimeout()).isEqualTo(30000);
    }
}