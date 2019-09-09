package com.spl.spl.config;


import com.spl.spl.oauth.CustomOAuth2Provider;
import com.spl.spl.service.users.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsersServiceImpl usersService;


    @Override
    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/webjars/**","/css/**","/img/**","/js/**","/vendor/**","/console/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        http
                .authorizeRequests().antMatchers("/admin","/chat/**","/RecordRoom","/soccer/**","/login","/join","/index","/oauth2/**","/register").permitAll()
                .antMatchers("/index2").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/chat/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/index")
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
                .and()
                .headers().frameOptions().sameOrigin()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/index"))
                .and()
                .formLogin()
                .loginPage("/index")
                .loginProcessingUrl("/loginCheck")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/customLogin")
                .failureUrl("/loginFailure")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .addFilterBefore(filter, CsrfFilter.class)
                .csrf().disable();
        http.sessionManagement()
                .maximumSessions(1) //중복 로그인 방지 1명만 로그인 가능
                .maxSessionsPreventsLogin(false) // 신규 로그인 사용자의 로그인이 허용 기존 사용자는 세션 아웃
                .and();
       /* http
                .authorizeRequests()
                .antMatchers("/", "/oauth2/**", "/join/**","/login/**",  "/css/**", "/img/**","/vendor/**", "/js/**", "/console/**").permitAll()
                .antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
                .antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
                .antMatchers("/naver").hasAuthority(NAVER.getRoleType())
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure")
                .and()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .formLogin()
                .successForwardUrl("/index2")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .addFilterBefore(filter, CsrfFilter.class)
                .csrf().disable();*/


      }




    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws  Exception{
        auth.userDetailsService(usersService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(OAuth2ClientProperties oAuth2ClientProperties, @Value("${custom.oauth2.kakao.client-id}") String kakaoClientId, @Value("${custom.oauth2.naver.client-id}") String NaverClientId) {


        List<ClientRegistration> registrations = oAuth2ClientProperties.getRegistration().keySet().stream()
                .map(client -> getRegistration(oAuth2ClientProperties, client))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());



        // 카카오 로그인 연동
        registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
                .clientId(kakaoClientId)
                .clientSecret("test")
                .jwkSetUri("test")
                .build());
        System.out.println(kakaoClientId);

        //Naver 연동
        registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver")
                .clientId(NaverClientId)
                .clientSecret("2H3hA5OAkp")
                .jwkSetUri("test")
                .build());
        System.out.println(NaverClientId);


        return new InMemoryClientRegistrationRepository(registrations);

    }








    private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
        if ("google".equals(client)) {
            OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");


            return CommonOAuth2Provider.GOOGLE.getBuilder(client)
                    .clientId(registration.getClientId())
                    .clientSecret(registration.getClientSecret())
                    .scope("email", "profile")
                    .build();
        }

        return null;
    }

    //패스워드 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
