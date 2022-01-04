package avengers.whois.domain.member;

import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.WorkerMemberDto;

public interface MemberService {

    void saveMember(WorkerMemberDto memberDto);

    String checkEmailW(String tempEmail);

    String checkEmailC(String tempEmail);

    void joinW(WorkerMemberDto workerMemberDto);

    void joinC(CorporateMemberDto corporateMemberDto);
}
