package uz.pdp.appshortlink.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appshortlink.payload.ApiResultDTO;
import uz.pdp.appshortlink.payload.SignDTO;
import uz.pdp.appshortlink.payload.SignUpDTO;
import uz.pdp.appshortlink.service.AuthService;
import uz.pdp.appshortlink.utils.AppConstants;

@Slf4j
@RestController
@RequestMapping(AppConstants.BASE_PATH_V1 + "/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-in")
    public ApiResultDTO<?> signIn(@RequestBody @Valid SignDTO signDTO) {
        return authService.signIn(signDTO);
    }

    @PostMapping("/sign-up")
    public ApiResultDTO<String> signUp(@RequestBody @Valid SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }

    @GetMapping("/email-verification")
    public ApiResultDTO<String> emailVerification(@RequestParam Integer codeId, @RequestParam Integer code) {
        return authService.emailVerification(codeId, code);
    }
}
