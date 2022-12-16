package hn.com.tigo.remision.filters;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
//@Component
public class AuthValidateFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
            insertLog(cachedBodyHttpServletRequest);
            filterChain.doFilter(cachedBodyHttpServletRequest,response);
        }catch (Exception ex) {
            response.setContentType("application/json");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            //TODO: se necesita hacer el set del objeto de respuesta general
            response.getWriter().write("Settear objeto error: "+ ex.getMessage());
        }
    }


    private void insertLog(CachedBodyHttpServletRequest request)  {
        List<String> methods = Arrays.asList("POST","PUT");
        try{
            String body="";

            if(methods.contains(request.getMethod().toUpperCase())) {
                body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
                String userField = request.getMethod().equalsIgnoreCase("POST") ? "createdBy": "modifiedBy";

                String a = jsonObject.get(userField).getAsString();
                log.info("{}",jsonObject);
                log.info("user field: {}",userField);
            }

            if ("GET".equalsIgnoreCase(request.getMethod())){

            }

            if("DELETE".equalsIgnoreCase(request.getMethod())) {

            }



        }catch (IOException |  RuntimeException e) {
            e.printStackTrace();
        }catch (Exception ie) {
            ie.printStackTrace();
        }


    }
}
