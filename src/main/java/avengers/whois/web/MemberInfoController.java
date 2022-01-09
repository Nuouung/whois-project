package avengers.whois.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/memberInfo")
@Controller
public class MemberInfoController {
	
	@GetMapping
	public String memberInfo() {
		return "/main/mypageMain";
	}
	

}
