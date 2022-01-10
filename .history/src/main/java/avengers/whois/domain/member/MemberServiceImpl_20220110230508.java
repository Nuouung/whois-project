package avengers.whois.domain.member;

import java.time.LocalDate;
import java.util.Optional;
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

    private final NewMemberRepository newMemberRepository;
    private final AdditionalInfoRepository additionalInfoRepository;
    private final PasswordEncoder pe;
    private final FileManager fileManager;

    @Override
    public String checkEmail(String tempEmail) {
        if (newMemberRepository.findById(tempEmail).isPresent()) {
            return "red";
        } else {
            return "green";
        }
    }

    @Override
    public void join(NewMemberDto newMemberDto, AdditionalInfoDto additionalInfoDto) {
        System.out.println("MemberServiceImpl > workerMemberDto : " + workerMemberDto);
        Set<String> aut = Set.of("ROLE_WORKER");
        WorkerMember data = WorkerMember.builder().email(workerMemberDto.getEmail())
                .password(pe.encode(workerMemberDto.getPassword())).name(workerMemberDto.getName())
                .phoneNumber(workerMemberDto.getPhoneNumber()).birthday(workerMemberDto.getBirthday())
                .gender(workerMemberDto.getGender()).finding(workerMemberDto.isFinding())
                .address(workerMemberDto.getAddress()).roles(aut).build();

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
    public AdditionalInfoDto getAdditionalS(String email, String password) {
        long number = workerMemberRepository.findByEmailAndPassword(email, password).get().getId();
        AdditionalInfo raw = additionalInfoRepository.getByWorkerMember_id(number);
        AdditionalInfoDto data = AdditionalInfoDto.builder().ono(raw.getOno()).prefJob(raw.getPrefJob())
                .prefMajor(raw.getPrefMajor()).prefExp(raw.getPrefExp()).expMonths(raw.getExpMonths())
                .workerMember(raw.getWorkerMember()).build();
        return data;
    }

    @Override
    public NewMemberDto getBasicS(String email) {
        Optional<NewMember> a = newMemberRepository.findById(email);
        if (a.isPresent()) {
            return a.get().toDtoWOpw();
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("MemberServiceImpl : parameter username " + username);

    }
}
