package avengers.whois.domain.member;

import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.WorkerMemberDto;

public interface MemberService {

    String checkEmailW(String tempEmail);

    String checkEmailC(String tempEmail);

    void joinW(WorkerMemberDto workerMemberDto, AdditionalInfoDto optionalInfoDto);

    void joinC(CorporateMemberDto corporateMemberDto, AdditionalInfoDto additionalInfoDto);

    AdditionalInfoDto getAdditionalS(String email, String password);

    WorkerMemberDto getBasicS(String email, String password);
}
