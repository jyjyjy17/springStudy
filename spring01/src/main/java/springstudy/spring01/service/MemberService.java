package springstudy.spring01.service;

import org.springframework.transaction.annotation.Transactional;
import springstudy.spring01.domain.Member;
import springstudy.spring01.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
//jpa를 사용 할 때 항상 트랜잭션이 필요하다. 왜?
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

     public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
    * 전체 회원 조회
    * */
    public List<Member> findMembers(){
        return memberRepository.findAll() ;
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
