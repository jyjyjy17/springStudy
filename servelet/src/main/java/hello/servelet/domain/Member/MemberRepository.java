package hello.servelet.domain.Member;

import javax.sound.midi.Sequence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 동시성 문제가 고려돼 있지 않으므로 실무에서는
* cocurrent HashMap , AtomicLong 사용 고려
* */
public class MemberRepository {
    private Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){
        //싱글톤을 위해 PRIVATE으로 생성함
    }
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
