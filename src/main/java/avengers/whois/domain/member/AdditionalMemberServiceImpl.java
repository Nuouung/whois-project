package avengers.whois.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdditionalMemberServiceImpl implements AdditionalMemberService {

    private final AdditionalInfoRepository additionalInfoRepository;

    /**
     * 아래 메소드들은 각각 '직무일치', '전공일치', '경력일치', '경력개월수일치'를 추출하는 메소드들이다.
     * controller에서 하나하나 가져다 쓸 수 있고, 결과를 뷰로 보내주면 된다.
     */
    public List<NewMember> findPrefJobForCorp(String email) {
        AdditionalInfo corpAdditionalInfo = additionalInfoRepository.findByNewMember_Email(email).orElse(null);
        return additionalInfoRepository.findMemberWithPrefJob('W', corpAdditionalInfo.getPrefJob());
    }

    public List<NewMember> findPrefMajorForCorp(String email) {
        AdditionalInfo corpAdditionalInfo = additionalInfoRepository.findByNewMember_Email(email).orElse(null);
        return additionalInfoRepository.findWorkerWithPrefMajor('W', corpAdditionalInfo.getPrefMajor());
    }

    public List<NewMember> findPrefExpForCorp(String email) {
        AdditionalInfo corpAdditionalInfo = additionalInfoRepository.findByNewMember_Email(email).orElse(null);
        return additionalInfoRepository.findWorkerWithPrefExp('W', corpAdditionalInfo.getPrefExp());
    }

    public List<NewMember> findEnoughExpMonthsForCorp(String email) {
        AdditionalInfo corpAdditionalInfo = additionalInfoRepository.findByNewMember_Email(email).orElse(null);
        return additionalInfoRepository.findWorkerWithExpMonths('W', corpAdditionalInfo.getExpMonths());
    }

}
