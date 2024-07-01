package user.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/member")
	public void forMember() {
		System.out.println("member");
	}
	
	@GetMapping("/manager")
	public void forManager() {
		System.out.println("manager");
	}
	
	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("admin");
	}
	
	@GetMapping("/login")
	public void login() {
	}

	@GetMapping("/loginSuccess")
	public void loginSuccess() {
	}
	
	@GetMapping("/accessDenied")
	public void  accessDenied() {
		
	}
	

}
