package avengers.whois.domain.member;

import avengers.whois.web.member.AdditionalInfoDto;

public interface MemberService {

    String checkEmail(String tempEmail);

    void join(NewMemberDto newMemberDto, AdditionalInfoDto optionalInfoDto);

    AdditionalInfoDto getAdditionalS(String email);

    NewMemberDto getBasicS(String email);
}
