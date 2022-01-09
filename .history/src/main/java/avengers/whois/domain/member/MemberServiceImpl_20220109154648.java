package avengers.whois.domain.member;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import avengers.whois.SecurityThings.SecureDTO;
import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.WorkerMemberDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final WorkerMemberRepository workerMemberRepository;
    private final CorporateMemberRepository corporateMemberRepository;
    private final AdditionalInfoRepository additionalInfoRepository;

    @Override
    public String checkEmail(String tempEmail) {
        if (workerMemberRepository.findByEmail(tempEmail).isPresent()
                || corporateMemberRepository.findByEmail(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

    @Override
    public void joinW(WorkerMemberDto workerMemberDto, AdditionalInfoDto additionalInfoDto) {
        List<GrantedAuthority> aut = of.(new SimpleGrantedAuthority("ROLE_WORKER"));
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
    public WorkerMemberDto getBasicS(String email, String password) {
        WorkerMember raw = workerMemberRepository.findByEmailAndPassword(email, password).get();

        return WorkerMemberDto.builder().id(raw.getId()).email(raw.getEmail()).password(raw.getPassword())
                .name(raw.getName())
                .phoneNumber(raw.getPhoneNumber()).birthday(raw.getBirthday()).gender(raw.getGender())
                .address(raw.getAddress()).finding(raw.isFinding()).createdDate(raw.getCreatedDate()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!workerMemberRepository.findByEmail(username).isPresent()) {
            new UsernameNotFoundException("UsernameNotFoundException");
            return null;
        } else {
            WorkerMember d = workerMemberRepository.findByEmail(username).get();
            SecureDTO data = new SecureDTO(d);
            return data;
        }
    }
}
