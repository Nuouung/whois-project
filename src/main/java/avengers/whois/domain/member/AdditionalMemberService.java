package avengers.whois.domain.member;

import java.util.List;

public interface AdditionalMemberService {

    List<NewMember> findPrefJobForCorp(String email);

    List<NewMember> findPrefMajorForCorp(String email);

    List<NewMember> findPrefExpForCorp(String email);

    List<NewMember> findEnoughExpMonthsForCorp(String email);

}
