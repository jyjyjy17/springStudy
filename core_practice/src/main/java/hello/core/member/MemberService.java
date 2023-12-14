package hello.core.member;

public interface MemberService {
    //회원가입
    void join(Member member);
    //id로 회원 찾기
    Member findMember(Long memberId);
}
