package avengers.whois.web.member;

import avengers.whois.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    // 일꾼전용 회원가입페이지로 이동요청 url
    @GetMapping("/join_worker")
    public String toJoinW() {
        return "member/joinFormW";
    }

    // 기업전용 회원가입페이지로 이동요청 url
    @GetMapping("/join_corporate")
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
    public AdditionalInfoDto getAdditional(String email) {
        return memberService.getAdditionalS(email);
    }

    @ResponseBody
    @PostMapping("/loadBasic")
    public NewMemberDto getBasic(String email) {
        return memberService.getBasicS(email);
    }

}
