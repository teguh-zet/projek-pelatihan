package com.salim.systempub.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/", "/auth/","/about/","/calladmin/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                .requestMatchers("/user/**").hasAuthority("USER")
                .requestMatchers("/bendahara/**").hasAnyAuthority("BENDAHARA","ADMIN")
                .requestMatchers("/sekretaris/**").hasAnyAuthority("SEKRETARIS","ADMIN")
                .requestMatchers("/ppmb/**").hasAnyAuthority("PPMB","ADMIN")
                .requestMatchers("/divisi/").hasAnyAuthority("DIVISI PENDIDIKAN DAN PELATIHAN","DIVISI PELATIHAN BAHASA INGGRIS","DIVISI KEROHANIAN","DIVISI KESEJAHTERAAN","DIVISI KESEHATAN","DIVISI KEASRAMAAN","DIVISI KEBERSIHAN","ADMIN")
                .requestMatchers("/divisi/pendidikan/**").hasAnyAuthority("DIVISI PENDIDIKAN DAN PELATIHAN","ADMIN")
                .requestMatchers("/divisi/pelatihaninggris/**").hasAnyAuthority("DIVISI PELATIHAN BAHASA INGGRIS","ADMIN")
                .requestMatchers("/divisi/kerohanian/**").hasAnyAuthority("DIVISI KEROHANIAN","ADMIN")
                .requestMatchers("/divisi/kesejahteraan/**").hasAnyAuthority("DIVISI KESEJAHTERAAN","ADMIN")
                .requestMatchers("/divisi/kesehatan/**").hasAnyAuthority("DIVISI KESEHATAN","ADMIN")
                .requestMatchers("/divisi/keasramaan/**").hasAnyAuthority("DIVISI KEASRAMAAN","ADMIN")
                .requestMatchers("/divisi/kebersihan/**").hasAnyAuthority("DIVISI KEBERSIHAN","ADMIN")
                .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/").permitAll()
                        .successHandler(new AuthenticationSuccessHandler() {
                            public void onAuthenticationSuccess(HttpServletRequest request,
                                    HttpServletResponse response, Authentication authentication)
                                    throws IOException, ServletException {
                                        AuthConfig.username=authentication.getName();
                                System.out.println("Logged As: " + authentication.getAuthorities().toString().replace("[","").replace("]",""));
                                if(authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("USER"))
                                    response.sendRedirect("/user/");
                                else if(authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("ADMIN"))
                                    response.sendRedirect("/admin/");
                                else if(authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("BENDAHARA"))
                                    response.sendRedirect("/bendahara/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("SEKRETARIS"))
                                    response.sendRedirect("/sekretaris/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("PPMB"))
                                    response.sendRedirect("/ppmb/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI PENDIDIKAN DAN PELATIHAN"))
                                    response.sendRedirect("/divisi/pendidikan/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI PELATIHAN BAHASA INGGRIS"))
                                    response.sendRedirect("/divisi/pelatihaninggris/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI KEROHANIAN"))
                                    response.sendRedirect("/divisi/kerohanian/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI KESEJAHTERAAN"))
                                    response.sendRedirect("/divisi/kesejahteran/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI KEASRAMAAN"))
                                    response.sendRedirect("/divisi/keasramaan/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI KEBERSIHAN"))
                                    response.sendRedirect("/divisi/kebersihan/");
                                else if (authentication.getAuthorities().toString().replace("[","").replace("]","").equalsIgnoreCase("DIVISI KESEHATAN"))
                                    response.sendRedirect("/divisi/kesehatan/");
                                else response.sendRedirect("/");
                            }
                        }))
                .logout((logout) -> logout
                        .logoutSuccessHandler(new LogoutSuccessHandler() {
                            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {
                                System.out.println("User logged out: " + authentication.getName());
                                response.sendRedirect("/");
                            }
                        }))
                .exceptionHandling(exception->exception
                    .accessDeniedPage("/denied"));

        return http.build();
    }
}
