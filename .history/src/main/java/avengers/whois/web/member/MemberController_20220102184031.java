package avengers.whois.web.member;

import avengers.whois.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String memberJoinForm(Model model) {
        model.addAttribute("member", new MemberDto());
        return "member/joinForm";
    }

    @PostMapping("/join")
    public String memberJoin(@ModelAttribute MemberDto member) {
        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping("/join_s")
    public String memberJoinForm_s() {
        return "member/join_s";
    }

    @PostMapping("/join_s")
    public String memberJoinForm_s(MemberDto memberDto) {
        memberService.join_s(memberDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/join_echeck")
    public String checkAvailabilityEmail(String tempEmail) {
        return memberService.checkEmail(tempEmail);
    }

}
