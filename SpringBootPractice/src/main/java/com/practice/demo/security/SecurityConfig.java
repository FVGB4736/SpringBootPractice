package com.practice.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


//@EnableWebSecurity 	觸發 Spring Security 配置機制
//@Bean註解並回傳 SecurityFilterChain 的方法是你用來寫安全策略的地方（基於 HttpSecurity）
//Spring Security 會把它讀取並應用到整個應用的過濾鏈中


@Configuration			//表示這是一個設定類別
@EnableWebSecurity		//啟用 Spring Security 的功能
public class SecurityConfig {
	
	@Autowired
	public SecurityConfig(CustomUserDetailsService userDetailService) {
		this.userDetailService = userDetailService;
	}
	
	//讓密碼存進資料庫時，會先進行加密
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private CustomUserDetailsService userDetailService;
	
	@Bean	//把這個方法的回傳值註冊到 Spring 容器中
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf(csrf -> csrf.disable())
        	.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/register","/register/new", "/clubs", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/clubs")
                .loginProcessingUrl("/login")
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout") // 用這個取代 AntPathMatcher
                    .logoutSuccessUrl("/login?logout=true")
                    .permitAll()
                );
		
		return http.build();
			
	}
	
//	public void configure (AuthenticationManagerBuilder authBuilder) throws Exception{
//		authBuilder.userDetailsService(userDetailService).passwordEncoder(SecurityConfig.passwordEncoder());
//	}
	
	// 上面是舊版SPRING的寫法，需要覆寫configure方法，並設定好userDetailsService和passwordEncoder
	// 下面是新版寫法，SPRING會自動找到userDetailService和passwordEncoder，並自動設定好
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
	
}
