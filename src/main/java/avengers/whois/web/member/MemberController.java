package avengers.whois.web.member;

import avengers.whois.domain.member.AdditionalMemberService;
import avengers.whois.domain.member.MemberService;
import avengers.whois.domain.member.NewMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AdditionalMemberService additionalMemberService;

    @GetMapping("/info")
    public String toMemberInfo() {
        return "member/memberInfo";
    }

    @GetMapping("/join_worker")
    public String toJoinW() {
        return "member/joinFormW";
    }

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

    // TODO 기업 / 회원별로 로그인 했을 때 메인 뷰가 다르게 출력되어야 한다.
    // 이를 위해서 로그인 버튼을 누르면 회원이 개인회원인지 기업회원인지 판별이 가능해야 한다.
    // (1) 새로운 방법을 생각할 필요가 있음!!!
    // 메인 뷰를 같이 쓰는 방안을 다시 한번 고려해 볼 필요성. (가져다 쓰는 데이터가 다름!!!)
    // ex. 기업의 필요 데이터 / 나와 '선호ㅇㅇ'가 맞는 인재
    // ex. 인재의 필요 데이터 / 나를 좋아요 한 기업
    // 필요한 데이터가 다르고 그렇기 때문에 사용해야 할 서비스 객체가 다름
    // (2) 뷰를 같이 사용하는 선택지도 가능함.
    // 현재(2022. 1. 13)는 기업 뷰만을 생각하고 로직을 작성. 이후 수정 필요
    @GetMapping("/main/{email}")
    public String mainPage(@PathVariable String email, Model model) {
        // 선호 직무 일치
        List<NewMember> prefJobList = additionalMemberService.findPrefJobForCorp(email);
        // 선호 전공 일치
        List<NewMember> prefMajorList = additionalMemberService.findPrefMajorForCorp(email);
        // 선호 경력 일치
        List<NewMember> prefExpList = additionalMemberService.findPrefExpForCorp(email);
        // 선호 개월수 일치
        List<NewMember> prefMonthsList = additionalMemberService.findEnoughExpMonthsForCorp(email);

        // 각각을 뷰 계층으로 전송
        model.addAttribute("prefJobList", prefJobList);
        model.addAttribute("prefMajorList", prefMajorList);
        model.addAttribute("prefExpList", prefExpList);
        model.addAttribute("prefMonthsList", prefMonthsList);
        return "/main/main_corp";
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
