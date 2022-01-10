package avengers.whois.web.member;

import avengers.whois.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

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
    public String wJoinSubmit(WorkerMemberDto workerMemberDto, AdditionalInfoDto additionalInfoDto) throws IOException {

        memberService.joinW(workerMemberDto, additionalInfoDto);
        System.out.println("contoller executed");
        return "redirect:/";
    }

    @PostMapping("/c_signup")
    public String cJoinSubmit(CorporateMemberDto corporateMemberDto, AdditionalInfoDto additionalInfoDto) {
        System.out.println("enterd Controller");
        memberService.joinC(corporateMemberDto, additionalInfoDto);
        System.out.println("contoller executed");
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/emailCheck")
    public String checkAvailabilityEmail(String tempEmail, char type) {
        return memberService.checkEmail(tempEmail, char type);
    }

    @ResponseBody
    @PostMapping("/loadAdditional")
    public AdditionalInfoDto getAdditional(String email, String password) {
        return memberService.getAdditionalS(email, password);
    }

    @ResponseBody
    @PostMapping("/loadBasic")
    public WorkerMemberDto getBasic(String email, String password) {
        return memberService.getBasicS(email, password);
    }

}
