package uz.pdp.appshortlink.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appshortlink.entity.Code;
import uz.pdp.appshortlink.entity.User;
import uz.pdp.appshortlink.enums.RoleTypeEnum;
import uz.pdp.appshortlink.exception.RestException;
import uz.pdp.appshortlink.payload.ApiResultDTO;
import uz.pdp.appshortlink.payload.SignDTO;
import uz.pdp.appshortlink.payload.SignUpDTO;
import uz.pdp.appshortlink.payload.TokenDTO;
import uz.pdp.appshortlink.repository.CodeRepository;
import uz.pdp.appshortlink.repository.RoleRepository;
import uz.pdp.appshortlink.repository.UserRepository;
import uz.pdp.appshortlink.security.JwtProvider;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationProvider provider;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final Random random;
    private final CodeRepository codeRepository;
    private final MailServiceImpl mailService;

    @Override
    @Cacheable(cacheNames = "user", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findByEmail(username);
        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("User not found by username ->%s".formatted(username));
        }

        return byEmail.get();
    }

    @Override
    public ApiResultDTO<?> signIn(SignDTO signDTO) {
        try {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signDTO.getEmail(), signDTO.getPassword());
            provider.authenticate(authentication);
            String token = jwtProvider.generateToken(signDTO.getEmail());
            return ApiResultDTO.success(new TokenDTO(token));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new RuntimeException(ApiResultDTO.error(e.getMessage()).toString());
        }
    }

    @Override
    public ApiResultDTO<String> signUp(SignUpDTO signUpDTO) {
        if (userRepository.existsByEmail(signUpDTO.getEmail()))
            throw RestException.alreadyExist("email");


        User user = new User(signUpDTO.getFirstName(),
                signUpDTO.getLastName(),
                signUpDTO.getEmail(),
                passwordEncoder.encode(signUpDTO.getPassword()),
                signUpDTO.getPhoneNumber(),
                roleRepository.findByType(RoleTypeEnum.USER).orElseThrow(),
                false);
        userRepository.save(user);

        Code code = new Code(user.getId(), random.nextInt(10000, 99999));

        codeRepository.save(code);

        mailService.sendMessage(user.getEmail(), "Verification code", code.getCode().toString());

        return ApiResultDTO.success("Ok");
    }

    @Override
    public ApiResultDTO<String> emailVerification(Integer codeId, Integer code) {
        Code codeEntity = codeRepository.findById(codeId).orElseThrow();
        if (!codeEntity.getCode().equals(code)) {
            throw RestException.restThrow("Wrong code ");
        }
        User user = userRepository.findById(codeEntity.getUserId()).orElseThrow();
        user.setEnabled(true);
        userRepository.save(user);
        codeRepository.delete(codeEntity);
        return ApiResultDTO.success("Ok");
    }

//    @CacheEvict(cacheNames = "user", allEntries = true)
//    @Scheduled(fixedRate = 20, timeUnit = TimeUnit.MINUTES)
//    public void deleteCache() {
//
//    }
}
