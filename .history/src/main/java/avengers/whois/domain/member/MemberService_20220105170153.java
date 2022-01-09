package avengers.whois.domain.member;

import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.OptionalInfoDto;
import avengers.whois.web.member.WorkerMemberDto;

public interface MemberService {

    String checkEmailW(String tempEmail);

    String checkEmailC(String tempEmail);

    void joinW(WorkerMemberDto workerMemberDto, OptionalInfoDto optionalInfoDto);

    void joinC(CorporateMemberDto corporateMemberDto);
}
