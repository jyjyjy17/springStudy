package springstudy.spring01.repository;

import org.springframework.transaction.annotation.Transactional;
import springstudy.spring01.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member ;

    }

    //pk기반은 이렇게 찾을 수 있지만,
    @Override
    public Optional<Member> findById(Long id) {
        Member member =  em.find(Member.class, id);
        return Optional.ofNullable(member);
    }
    //나머지는 sql 작성해줘야 한다. spring data jpa를 쓰면 이것도 대체 가능하다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //id, name 을 select 하는게 아니라 이미 매핑된 member를 들고 올 수 있다. 어떻게 되는거지?
        return em.createQuery("select m from Member m ", Member.class).getResultList();
    }
}
