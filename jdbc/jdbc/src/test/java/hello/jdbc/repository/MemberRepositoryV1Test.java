package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach() {
        //기본 driverManager를 통한 항상 새로운 커넥션 획득
        //DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        HikariDataSource datasource = new HikariDataSource();
        datasource.setJdbcUrl(URL);
        datasource.setUsername(USERNAME);
        datasource.setPassword(PASSWORD);
        datasource.setMaximumPoolSize(10);
        datasource.setPoolName("Mypool");
        //이름 지정해서 뭐하지?

        repository = new MemberRepositoryV1(datasource);
    }

    @Test
    void crud() throws SQLException {
//save
        Member member = new Member("memberV33W", 10000);
        repository.save(member);
//findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);

        //update: money: 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);
//delete
        repository.delete(member.getMemberId());
        // ()를 호출하면 . 아래 예외가 터진다.
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}