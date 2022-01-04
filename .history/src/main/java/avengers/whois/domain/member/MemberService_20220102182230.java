package avengers.whois.domain.member;

import avengers.whois.web.member.MemberDto;

public interface MemberService {

    void saveMember(MemberDto memberDto);

    void join_s(MemberDto memberDto);

    boolean checkEmail(String tempEmail);
}
