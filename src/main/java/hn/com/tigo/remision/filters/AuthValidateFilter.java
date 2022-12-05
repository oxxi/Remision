package hn.com.tigo.remision.filters;

import hn.com.tigo.remision.exceptions.UnAuthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Component
public class AuthValidateFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
          //  String userName= validateHeaderAuth(request);
           // UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userName,userName);
           // SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }catch (Exception ex) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            //TODO: se necesita hacer el set del objeto de respuesta general
            response.getWriter().write("Settear objeto error: "+ ex.getMessage());
        }
    }


    private String validateHeaderAuth(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null || header.isEmpty()){
            throw new UnAuthorizedException("Token didnt exist");
        }
        return header;
    }
}
