package uz.pdp.appshortlink.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.pdp.appshortlink.payload.ApiResultDTO;
import uz.pdp.appshortlink.payload.SignDTO;
import uz.pdp.appshortlink.payload.SignUpDTO;

import java.util.UUID;

public interface AuthService extends UserDetailsService {
    ApiResultDTO<?> signIn(SignDTO signDTO);

    ApiResultDTO<String> signUp(SignUpDTO signUpDTO);

    ApiResultDTO<String> emailVerification(Integer codeId, Integer code);
}
