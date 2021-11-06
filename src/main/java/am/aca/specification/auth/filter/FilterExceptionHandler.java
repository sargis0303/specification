//package am.aca.specification.auth.filter;
//
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.exceptions.TokenExpiredException;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class FilterExceptionHandler extends OncePerRequestFilter {
//
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            filterChain.doFilter(request, response);
//        } catch (TokenExpiredException e) {
//            respond(response, HttpStatus.UNAUTHORIZED, ErrorCodes.ACCESS_TOKEN_EXPIRED, e.getMessage());
//        } catch (JWTVerificationException e) {
//            respond(response, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), e.getMessage());
//        } catch (RuntimeException e) {
//            respond(response, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
//        }
//    }
//
//    private void respond(HttpServletResponse response, HttpStatus httpStatus, int customErrorCode, String message) throws IOException {
//        ResponseError responseError = ResponseError
//                .builder()
//                .code(customErrorCode)
//                .message(message)
//                .build();
//
//        response.setStatus(httpStatus.value());
//        response.setContentType("application/json");
//
//        response.getWriter().write(convertObjectToJson(responseError));
//    }
//
//    public String convertObjectToJson(Object object) throws JsonProcessingException {
//        if (object == null) {
//            return null;
//        }
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(object);
//    }
//}
