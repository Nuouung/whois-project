package avengers.whois.web.member;

import avengers.whois.domain.member.MemberService;
import avengers.whois.domain.member.NewMemberDto;
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

    @PostMapping("/signup")
    public String joinSubmit(NewMemberDto newMemberDto, AdditionalInfoDto additionalInfoDto) throws IOException {
        memberService.join(newMemberDto, additionalInfoDto);
        System.out.println("contoller executed");
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/emailCheck")
    public String checkAvailabilityEmail(String tempEmail) {
        return memberService.checkEmail(tempEmail);
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
