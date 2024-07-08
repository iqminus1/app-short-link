package uz.pdp.appshortlink.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.pdp.appshortlink.service.AuthService;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        checkToken(request, response);

        filterChain.doFilter(request, response);
    }

    private void checkToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String substring = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.isNull(substring)) {
            return;
        }

        if (!substring.startsWith("bearer ")) return;

        String token = substring.substring(7);
        String username = null;
        try {
            username = jwtProvider.getSubject(token);
        } catch (Exception e) {
            response.sendError(401);
        }

        UserDetails userDetails = authService.loadUserByUsername(username);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        ));

    }
}
