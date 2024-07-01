package user.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		
		security.csrf(AbstractHttpConfigurer :: disable)
				.authorizeHttpRequests(auth -> {
					auth.requestMatchers(new AntPathRequestMatcher("/member/**"),
							new AntPathRequestMatcher("/js/**")).authenticated() // js는 외부 js파일을 가져왔을 때 실행시	// 인증만 되면 ok
						.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")				// ADMIN권한만
						.requestMatchers(new AntPathRequestMatcher("/manager/**")).hasAnyRole("MANAGER","ADMIN")// 권한 여러개에 대해
						.anyRequest().permitAll();																// 인증, 권한 상관 없음
				});
		
		security.formLogin(formLogin -> formLogin.loginPage("/login").defaultSuccessUrl("/loginSuccess", true)) // 시큐리티가 제공하는 기본 로그인 form을 사용
				.logout(logout -> {logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
										.invalidateHttpSession(true).logoutSuccessUrl("/");})
				.exceptionHandling(exception -> exception.accessDeniedPage("/accessDenied"));
		
		return security.build();
	}
// {noop} : 암호화 처리 x
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("manager")
		.password("{noop}manager123")
		.roles("MANAGER");
		
		auth.inMemoryAuthentication().withUser("admin")
		.password("{noop}admin123")
		.roles("ADMIN");
	}
	

}
