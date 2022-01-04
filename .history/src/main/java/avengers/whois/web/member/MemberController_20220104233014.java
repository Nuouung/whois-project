package avengers.whois.web.member;

import avengers.whois.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String memberJoinForm_s() {
        return "member/join_s";
    }

    @PostMapping("/signup")
    public String memberJoinForm_s(MemberDto memberDto) {
        memberService.join(memberDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/join_echeck")
    public String checkAvailabilityEmail(String tempEmail) {
        return memberService.checkEmail(tempEmail);
    }

}
