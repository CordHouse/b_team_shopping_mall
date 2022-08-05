package com.example.b_team_shopping_mall.service;

import com.example.b_team_shopping_mall.entity.Register;
import com.example.b_team_shopping_mall.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterRepository registerRepository;

    // 회원가입 함수
    @Transactional
    public Register signUp(Register register){
        Register register1 = new Register(register.getUsername(), register.getUserid(), register.getUserpw(), register.getUseremail());
        registerRepository.save(register1);
        return register1;
    }

    // 로그인 함수
    @Transactional
    public String login(Register register){
        List<Register> registerList = registerRepository.findAll();
        while(!registerList.isEmpty()){
            Register registerPop = registerList.remove(0);
            if(registerPop.getUserid().equals(register.getUserid()))
                return "로그인 성공";
        }
        return "로그인 실패";
    }
}
