package avengers.whois.domain.member;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import avengers.whois.web.member.MemberDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(MemberDto memberDto) {
        Member member = Member.createMember(memberDto);
        validateDuplicatedMember(memberDto.getEmail());
        memberRepository.save(member);
    }

    private void validateDuplicatedMember(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            // TODO 튕기는 로직
        }
    }

    @Override
    public void join_s(MemberDto memberDto) {
        Member member = Member.createMember(memberDto);
        memberRepository.save(member);
    }

    @Override
    public int checkEmail(String tempEmail, Model m) {
        if (memberRepository.findByEmail(tempEmail).isPresent()) {
            System.out.println("service : not available");
            m.addAttribute("emailCheck", "#ff286e");
            return "#ff286e";
        } else {
            System.out.println("service : available");
            m.addAttribute("emailCheck", "#3e3e3e");
            return "#3e3e3e";
        }
    }

}
