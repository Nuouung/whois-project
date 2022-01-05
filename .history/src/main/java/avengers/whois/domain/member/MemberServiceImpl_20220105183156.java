package avengers.whois.domain.member;

import org.springframework.stereotype.Service;

import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.WorkerMemberDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final WorkerMemberRepository workerMemberRepository;
    private final CorporateMemberRepository corporateMemberRepository;
    private final AdditionalInfoRepository additionalInfoRepository;

    @Override
    public String checkEmailW(String tempEmail) {
        if (workerMemberRepository.findByEmail(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

    @Override
    public String checkEmailC(String tempEmail) {
        if (corporateMemberRepository.findByEmail(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

    @Override
    public void joinW(WorkerMemberDto workerMemberDto, AdditionalInfoDto additionalInfoDto) {
        WorkerMember data = WorkerMember.builder().email(workerMemberDto.getEmail())
                .password(workerMemberDto.getPassword()).name(workerMemberDto.getName())
                .phoneNumber(workerMemberDto.getPhoneNumber()).birthday(workerMemberDto.getBirthday())
                .gender(workerMemberDto.getGender()).finding(workerMemberDto.isFinding())
                .address(workerMemberDto.getAddress()).build();
        AdditionalInfo inData = AdditionalInfo.builder().prefJob(additionalInfoDto.getPrefJob())
                .prefMajor(additionalInfoDto.getPrefMajor())
                .prefExp(additionalInfoDto.getPrefExp()).expMonths(additionalInfoDto.getExpMonths())
                .workerMember(workerMemberRepository.save(data)).build();
        additionalInfoRepository.save(inData);
    }

    @Override
    public void joinC(CorporateMemberDto corporateMemberDto, AdditionalInfoDto additionalInfoDto) {
        CorporateMember data = CorporateMember.builder().email(corporateMemberDto.getEmail())
                .password(corporateMemberDto.getPassword()).repName(corporateMemberDto.getRepName())
                .repPhoneNumber(corporateMemberDto.getRepPhoneNumber()).corpNo(corporateMemberDto.getCorpNo())
                .corpName(corporateMemberDto.getCorpName()).industryField(corporateMemberDto.getIndustryField())
                .corpAddress(corporateMemberDto.getCorpAddress())
                .establishedDate(corporateMemberDto.getEstablishedDate()).build();
        AdditionalInfo inData = AdditionalInfo.builder().prefJob(additionalInfoDto.getPrefJob())
                .prefMajor(additionalInfoDto.getPrefMajor())
                .prefExp(additionalInfoDto.getPrefExp()).expMonths(additionalInfoDto.getExpMonths())
                .corporateMember(corporateMemberRepository.save(data)).build();
        additionalInfoRepository.save(inData);
    }

    @Override
    public AdditionalInfoDto getAdditionalS(String email, String password) {
        long number = workerMemberRepository.findByEmailAndPassword(email, password).get().getId();
        AdditionalInfo raw = additionalInfoRepository.getByWorkerMember_id(number);
        AdditionalInfoDto data = AdditionalInfoDto.builder().ono(raw.getOno()).prefJob(raw.getPrefJob())
                .prefMajor(raw.getPrefMajor()).prefExp(raw.getPrefExp()).expMonths(raw.getExpMonths())
                .workerMember(raw.getWorkerMember()).build();
        return data;
    }

    @Override
    public AdditionalInfoDto getBasicS(String email, String password) {
        long number = workerMemberRepository.findByEmailAndPassword(email, password).get().getId();
        AdditionalInfo raw = additionalInfoRepository.getByWorkerMember_id(number);
        return null;
    }
}
