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

    @GetMapping("/info")
    public String toMemberInfo() {
        return "/member/memberInfo";
    }

    @GetMapping("/w_signup")
    public String toJoinW() {
        return "member/joinFormW";
    }

    @GetMapping("/c_signup")
    public String toJoinC() {
        return "member/joinFormC";
    }

    @PostMapping("/w_signup")
    public String wJoinSubmit(WorkerMemberDto workerMemberDto, AdditionalInfoDto additionalInfoDto) {
        memberService.joinW(workerMemberDto, additionalInfoDto);
        return "redirect:/";
    }

    @PostMapping("/c_signup")
    public String cJoinSubmit(CorporateMemberDto corporateMemberDto, AdditionalInfoDto additionalInfoDto) {
        memberService.joinC(corporateMemberDto, additionalInfoDto);
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/emailCheck_W")
    public String checkAvailabilityEmail_W(String tempEmail) {
        return memberService.checkEmailW(tempEmail);
    }

    @ResponseBody
    @PostMapping("/emailCheck_C")
    public String checkAvailabilityEmail_C(String tempEmail) {
        return memberService.checkEmailC(tempEmail);
    }

    @ResponseBody
    @PostMapping("/loadAdditional")
    public AdditionalInfoDto getAdditional(String email, String password) {
        return memberService.getAdditionalS(email, password);
    }

}
