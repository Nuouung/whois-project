package avengers.whois.domain.member;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import avengers.whois.domain.file.FileManager;
import org.springframework.stereotype.Service;

import avengers.whois.SecurityThings.SecureDTO;
import avengers.whois.web.member.AdditionalInfoDto;
import avengers.whois.web.member.NewMemberDto;
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
        NewMember data;
        AdditionalInfo addData;
        switch (newMemberDto.getMemberType()) {
            case 'c':
                data = NewMember.builder().memberType(newMemberDto.getMemberType())
                        .email(newMemberDto.getEmail())
                        .password(pe.encode(newMemberDto.getPassword()))
                        .roles(Set.of("ROLE_CORP", "ROLE_USER"))
                        .name(newMemberDto.getName()).phoneNumber(newMemberDto.getPhoneNumber())
                        .birthDate(newMemberDto.getBirthDate())
                        .address(newMemberDto.getAddress()).gender(newMemberDto.getGender())
                        .finding(newMemberDto.isFinding()).build();
                addData = AdditionalInfo.builder().prefJob(additionalInfoDto.getPrefJob())
                        .prefMajor(additionalInfoDto.getPrefMajor())
                        .prefExp(additionalInfoDto.getPrefExp()).expMonths(additionalInfoDto.getExpMonths())
                        .newMember(NewMember.builder().email(newMemberRepository.save(data).getEmail()).build())
                        .build();
                additionalInfoRepository.save(addData);
                break;
            case 'w':
                data = NewMember.builder().memberType(newMemberDto.getMemberType())
                        .email(newMemberDto.getEmail())
                        .password(pe.encode(newMemberDto.getPassword()))
                        .roles(Set.of("ROLE_WORK", "ROLE_USER"))
                        .name(newMemberDto.getName()).phoneNumber(newMemberDto.getPhoneNumber())
                        .birthDate(newMemberDto.getBirthDate())
                        .address(newMemberDto.getAddress()).corpNo(newMemberDto.getCorpNo())
                        .corpName(newMemberDto.getCorpName()).industryField(newMemberDto.getIndustryField()).build();
                addData = AdditionalInfo.builder().prefJob(additionalInfoDto.getPrefJob())
                        .prefMajor(additionalInfoDto.getPrefMajor())
                        .prefExp(additionalInfoDto.getPrefExp()).expMonths(additionalInfoDto.getExpMonths())
                        .newMember(NewMember.builder().email(newMemberRepository.save(data).getEmail()).build())
                        .build();
                additionalInfoRepository.save(addData);
                break;
        }
    }

    // private void storeFiles(WorkerMemberDto workerMemberDto, WorkerMember data)
    // throws IOException {
    // if (workerMemberDto.getFname() != null) {
    // String convertedFName =
    // fileManager.convertAndStore(workerMemberDto.getFname());
    // data.setFName(convertedFName);
    // }

    // if (workerMemberDto.getResume() != null) {
    // String convertedResume =
    // fileManager.convertAndStore(workerMemberDto.getResume());
    // data.setResume(convertedResume);
    // }
    // }

    @Override
    public AdditionalInfoDto getAdditionalS(String email) {
        Optional<AdditionalInfo> a = additionalInfoRepository.findByNewMember_Email(email);
        if (a.isPresent()) {
            return a.get().toDto();
        }
        return null;
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

    // 얜 세션만들라고 하는거니까 상속받는 변수 username, password, authorities랑 비교적 필수적인 membertype만
    // 있는 SecureDTO돌려줌
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " + username);
        String[] divided = username.split("/");
        Optional<NewMember> a = newMemberRepository.findById(username);
        if (a.isPresent()) {
            return new SecureDTO(a.get());
        } else {
            throw new UsernameNotFoundException("User with " + username + " does not exist");
        }
    }
}
