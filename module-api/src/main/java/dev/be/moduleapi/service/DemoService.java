package dev.be.moduleapi.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.be.moduleapi.exception.CustomException;
import dev.be.modulecommon.domain.Member;
import dev.be.modulecommon.enums.response.CodeEnum;
import dev.be.modulecommon.repositories.MemberRepository;
import dev.be.modulecommon.service.CommonDemoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {

    @Value("${profile-name}")
    private String name;

    private final MemberRepository memberRepository;

    private final CommonDemoService commonDemoService;

    public String save() {
        log.info("env profile : ",name);
        log.info("commonDemoService.getName() : " , commonDemoService.getModuleName());

        Member newMember = memberRepository.save(Member.builder()
                                                       .name(Thread.currentThread().getName())
                                                       .build());
        return newMember.getName();
    }

    public String find() {
        return String.valueOf(memberRepository.findAll().size());
    }

    public String handleCustomException() {
        if (true) {
            log.info("Throw CustomException");
            throw new CustomException(CodeEnum.NOT_IDENTITY_VERIFIED_USER);
        }

        return "exception";
    }
}
