package avengers.whois.web.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("for/test")
public class tempController_by_jinseok {

    @GetMapping("/matching/p")
    public String matchingP() {
        return "matching_p";
    }

    @GetMapping("/C")
    public String mainC() {
        return "/main/main_corp";
    }

    @GetMapping("/W")
    public String mainW() {
        return "/main/main_worker";
    }

}
