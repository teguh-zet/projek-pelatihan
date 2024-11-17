package com.salim.systempub.config;

import org.springframework.beans.factory.annotation.Autowired;

import com.salim.systempub.repository.AuthRepository;
import com.salim.systempub.repository.GenerationRepository;
import com.salim.systempub.repository.MemberPaggingRepository;
import com.salim.systempub.repository.MemberRepository;

public class ServiceConfig {
    @Autowired
    public AuthRepository authRepository;
    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    public GenerationRepository generationRepository;
    @Autowired
    public MemberPaggingRepository memberPaggingRepository;
}