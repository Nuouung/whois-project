package avengers.whois.domain.member;

import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.WorkerMemberDto;

import java.io.IOException;

public interface MemberService {

    String checkEmail(String tempEmail, char type);

    void joinW(WorkerMemberDto workerMemberDto, AdditionalInfoDto optionalInfoDto) throws IOException;

    void joinC(CorporateMemberDto corporateMemberDto, AdditionalInfoDto additionalInfoDto);

    AdditionalInfoDto getAdditionalS(String email, String password);

    WorkerMemberDto getBasicS(String email, String password);

    void test1();
}
