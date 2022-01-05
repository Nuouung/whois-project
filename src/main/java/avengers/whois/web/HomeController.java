package avengers.whois.web;

import avengers.whois.web.member.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String toHome(Model model) {
        model.addAttribute("member", new LoginDto());
        return "home";
    }
}
