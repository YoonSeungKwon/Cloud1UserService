package yoon.test.cloud.testService1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yoon.test.cloud.testService1.domain.Members;
import yoon.test.cloud.testService1.dto.request.LoginDto;
import yoon.test.cloud.testService1.dto.request.RegisterDto;
import yoon.test.cloud.testService1.dto.response.MemberResponse;
import yoon.test.cloud.testService1.enums.Role;
import yoon.test.cloud.testService1.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private MemberResponse toResponse(Members members){
        return new MemberResponse(members.getEmail(), members.getName(), members.getRoleKey(), members.getRegdate().toString());
    }

    public MemberResponse register(RegisterDto dto){

        Members member = Members.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .role(Role.USER)
                .build();

        return toResponse(memberRepository.save(member));
    }

    public MemberResponse login(LoginDto dto)throws UsernameNotFoundException,BadCredentialsException{
        String username = dto.getEmail();
        String password = dto.getPassword();
        Members member = memberRepository.findMembersByEmail(username);
        if(member == null)
            throw new UsernameNotFoundException(username);
        if(!passwordEncoder.matches(password, member.getPassword()))
            throw new BadCredentialsException(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return toResponse(member);
    }

}
