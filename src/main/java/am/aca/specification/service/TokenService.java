//package am.aca.specification.service;
//
//import am.aca.specification.auth.CurrentUser;
//import am.aca.specification.auth.Token;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//@Service
//@Slf4j
//public class TokenService {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);
//
//    public static final String SECRET = "nuBP0TjyfeBSywdB7YZgzWVhTRJ3y6dUHFEon8P0eizySOBLhp1D1lK9CI4oRTZx2eHCCvrRh7FSq65t5gV03oFUEafRHb34EFUD";
//    public static final String TOKEN_PREFIX = "Bearer ";
//    public static final String HEADER = "Authorization";
//    public static final long REFRESH_TOKEN_EXPIRATION_TIME = 14*86400*1000; // 14 days
//    private static final long ACCESS_TOKEN_EXPIRATION_TIME = 3*60*1000; // 3 minutes
//
//    private Authentication getAuthentication() {
//        CurrentUser currentUser = new CurrentUser(
//                1L, "test@test.test",
//                "$2a$12$yhNrCSP3FSEIfir0pcC9p.DrsPo75duUWGArKqzCW/fO0oQoVGUF2", // test
//                true,
//                "USER"
//        );
//
//        return new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
//    }
//
//    @Transactional
//    public void logout(String refreshToken) {
//        SecurityContextHolder.clearContext();
//
//        LOGGER.info("Logout with refresh token {}", refreshToken);
//    }
//
//    public Token generateResponseToken(Authentication authentication) {
//        LOGGER.info("Generated response token for user {}", authentication.getName());
//        return  Token.builder()
//                .accessToken(generateJwtToken(authentication))
//                .accessTokenExpirationDate(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME * 10))
//                .build();
//    }
//
//    private String generateJwtToken(Authentication authentication) {
//        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
//        try {
//            LOGGER.info("Generated jwt token for {}", authentication.getName());
//            return JWT.create()
//                    .withSubject(currentUser.getUsername())
//                    .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME * 10))
//                    .withClaim("id", currentUser.getId())
//                    .withClaim("role", getRole(authentication))
//                    .sign(Algorithm.HMAC512(SECRET));
//        } catch (UnsupportedEncodingException e) {
//            LOGGER.warn("Failed to generate jwt for {}", authentication.getName());
//            throw new AppRuntimeException("Fail in token generation");
//        }
//    }
//
//    private String getRole(Authentication authentication) {
//        List<String> roles = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//        return roles.get(0);
//    }
//
//    public String getRandomToken() {
//        return UUID.randomUUID().toString();
//    }
//}
