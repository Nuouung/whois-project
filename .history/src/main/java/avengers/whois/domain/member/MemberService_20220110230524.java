package avengers.whois.domain.member;

import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.WorkerMemberDto;

public interface MemberService {

    String checkEmail(String tempEmail);

    void joinW(NewMemberDto newMemberDto, AdditionalInfoDto optionalInfoDto);

    AdditionalInfoDto getAdditionalS(String email);

    NewMemberDto getBasic(String email);
}
