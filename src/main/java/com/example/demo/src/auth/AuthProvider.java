package com.example.demo.src.auth;


import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//Provider : Read의 비즈니스 로직 처리
@RequiredArgsConstructor
@Service
@Slf4j
public class AuthProvider {

    private final AuthRepository userDao;
    private final JwtService jwtService;


}
