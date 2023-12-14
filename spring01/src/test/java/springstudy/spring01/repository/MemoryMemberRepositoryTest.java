package springstudy.spring01.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import springstudy.spring01.domain.Member;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("srping");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();

        Assertions.assertEquals(member,result);
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("sp1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sp2");
        repository.save(member2);

        Member result = repository.findByName("sp1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("sp1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("sp2");
        repository.save(member2);

        List<Member> result = repository.findAll() ;
        assertThat(result.size()).isEqualTo(2);

    }


}
