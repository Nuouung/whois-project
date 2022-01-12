package avengers.whois.domain.member;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import avengers.whois.domain.file.FileManager;
import org.springframework.stereotype.Service;

import avengers.whois.SecurityThings.SecureDTO;
import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.CorporateMemberDto;
import avengers.whois.web.member.WorkerMemberDto;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final WorkerMemberRepository workerMemberRepository;
    private final CorporateMemberRepository corporateMemberRepository;
    private final AdditionalInfoRepository additionalInfoRepository;
    private final PasswordEncoder pe;
    private final FileManager fileManager;

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
    public void joinW(WorkerMemberDto workerMemberDto, AdditionalInfoDto additionalInfoDto) throws IOException {
        System.out.println("MemberServiceImpl > workerMemberDto : " + workerMemberDto);
        Set<String> aut = Set.of("ROLE_WORKER");
        WorkerMember data = WorkerMember.builder().email(workerMemberDto.getEmail())
                .password(pe.encode(workerMemberDto.getPassword())).name(workerMemberDto.getName())
                .phoneNumber(workerMemberDto.getPhoneNumber()).birthday(workerMemberDto.getBirthday())
                .gender(workerMemberDto.getGender()).finding(workerMemberDto.isFinding())
                .address(workerMemberDto.getAddress()).roles(aut).build();

        // 이력서, 포트폴리오 db에 반영 로직
        storeFiles(workerMemberDto, data);

        AdditionalInfo inData = AdditionalInfo.builder().prefJob(additionalInfoDto.getPrefJob())
                .prefMajor(additionalInfoDto.getPrefMajor())
                .prefExp(additionalInfoDto.getPrefExp()).expMonths(additionalInfoDto.getExpMonths())
                .workerMember(workerMemberRepository.save(data)).build();
        additionalInfoRepository.save(inData);
    }

    private void storeFiles(WorkerMemberDto workerMemberDto, WorkerMember data) throws IOException {
        if (workerMemberDto.getFname() != null) {
            String convertedFName = fileManager.convertAndStore(workerMemberDto.getFname());
            data.setFName(convertedFName);
        }

        if (workerMemberDto.getResume() != null) {
            String convertedResume = fileManager.convertAndStore(workerMemberDto.getResume());
            data.setResume(convertedResume);
        }
    }

    @Override
    public void joinC(CorporateMemberDto corporateMemberDto, AdditionalInfoDto additionalInfoDto) {
        Set<String> aut = Set.of("ROLE_CORP");
        CorporateMember data = CorporateMember.builder().email(corporateMemberDto.getEmail())
                .password(pe.encode(corporateMemberDto.getPassword())).repName(corporateMemberDto.getRepName())
                .repPhoneNumber(corporateMemberDto.getRepPhoneNumber()).corpNo(corporateMemberDto.getCorpNo())
                .corpName(corporateMemberDto.getCorpName()).industryField(corporateMemberDto.getIndustryField())
                .corpAddress(corporateMemberDto.getCorpAddress())
                .establishedDate(corporateMemberDto.getEstablishedDate()).roles(aut).build();
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
        System.out.println("MemberServiceImpl : parameter username " + username);
        if (!workerMemberRepository.findByEmail(username).isPresent()) {
            new UsernameNotFoundException("UsernameNotFoundException");
            return null;
        } else {
            WorkerMember d = workerMemberRepository.findByEmail(username).get();
            System.out.println(d);
            SecureDTO data = new SecureDTO(d);
            System.out.println("MemberServiceImpl>else : SecureDTO " + data);
            return data;
        }
    }

    @Override
    public void test1() {
        WorkerMember test = WorkerMember.builder().email("worker@test.com").password(pe.encode("1234567890!"))
                .name("name")
                .phoneNumber("01011112222").birthday(LocalDate.now()).gender('F').finding(false).address("aaaaa")
                .roles(Set.of("ROLE_WORKER")).build();
        System.out.println(workerMemberRepository.save(test));
    }
}
