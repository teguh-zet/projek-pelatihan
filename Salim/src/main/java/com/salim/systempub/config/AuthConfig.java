package com.salim.systempub.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.salim.systempub.repository.AuthRepository;
import com.salim.systempub.repository.GenerationRepository;
import com.salim.systempub.repository.MemberRepository;
import com.salim.systempub.services.AuthService;
import com.salim.systempub.services.MemberService;

public class AuthConfig {

    @Autowired
    public AuthRepository authRepository;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public GenerationRepository genRepository;

    @Autowired
    public AuthService authService;
    @Autowired
    public MemberService memberService;

    public final String ADMIN_USERNAME="admin";
    public final String ADMIN_PASSWORD="admin123";
    public static String number="";
    public static String contact="";
    public static String messageAdmin="";
    public static String username ="admin";
    public static Long buffId=0L;
}