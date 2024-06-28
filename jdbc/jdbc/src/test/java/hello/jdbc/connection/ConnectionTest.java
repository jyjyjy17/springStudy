package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j

public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con1.getClass());

    }

    @Test
    void dataSourceDriverManager() {
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    }
    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        HikariDataSource datasource = new HikariDataSource();
        datasource.setJdbcUrl(URL);
        datasource.setUsername(USERNAME);
        datasource.setPassword(PASSWORD);
        datasource.setMaximumPoolSize(10);
        datasource.setPoolName("Mypool");
        //이름 지정해서 뭐하지?

        useDataSource(datasource);
        Thread.sleep(1000);
    }
}
