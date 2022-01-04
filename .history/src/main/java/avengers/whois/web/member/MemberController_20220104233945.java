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

    @GetMapping("/w_signup")
    public String toJoinW() {
        return "member/joinFormW";
    }

    @GetMapping("/c_signup")
    public String toJoinC() {
        return "member/joinFormC";
    }

    @PostMapping("/w_signup")
    public String wJoinSubmit(WorkerMemberDto workerMemberDto) {
        memberService.joinW(workerMemberDto);
        return "redirect:/";
    }

    @PostMapping("/c_signup")
    public String cJoinSubmit(CorporateMemberDto corporateMemberDto) {
        memberService.joinC(corporateMemberDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/join_echeck")
    public String checkAvailabilityEmail(String tempEmail) {
        return memberService.checkEmail(tempEmail);
    }

}
