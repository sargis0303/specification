//package am.aca.specification.auth.filter;
//
//import am.aca.specification.auth.CurrentUser;
//import am.aca.specification.service.TokenService;
//import am.aca.specification.service.dto.AuthRequestUserDto;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final AuthenticationManager authenticationManager;
//    private final TokenService tokenService;
//
//    private final ObjectMapper objectMapper;
//
//    public AuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService) {
//        this.authenticationManager = authenticationManager;
//        this.tokenService = tokenService;
//
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            AuthRequestUserDto requestUser = objectMapper.readValue(request.getInputStream(), AuthRequestUserDto.class);
//
//            UserDetails currentUser = new CurrentUser(
//                    1L, "test@test.test",
//                    "$2a$12$yhNrCSP3FSEIfir0pcC9p.DrsPo75duUWGArKqzCW/fO0oQoVGUF2", // test
//                    true,
//                    "USER"
//            );
//
//            Authentication authentication =
//                    new UsernamePasswordAuthenticationToken(currentUser, requestUser.getPassword(), currentUser.getAuthorities());
//            authenticationManager.authenticate(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return authentication;
//        } catch (IOException e) {
//            throw new AppRuntimeException(e.toString());
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//                                            FilterChain chain, Authentication authResult) throws IOException {
//
//        String responseTokenToJson = objectMapper.writeValueAsString(tokenService.generateResponseToken(authResult));
//
//        response.setContentType("application/json");
//        response.getWriter().write(responseTokenToJson);
//
//        response.flushBuffer();
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//
//        ResponseError error = ResponseError
//                .builder()
//                .code(HttpStatus.BAD_REQUEST.value())
//                .message("Invalid username or password")
//                .build();
//
//        response.setContentType("application/json");
//
//        response.getWriter().write(objectMapper.writeValueAsString(error));
//    }
//
//}
