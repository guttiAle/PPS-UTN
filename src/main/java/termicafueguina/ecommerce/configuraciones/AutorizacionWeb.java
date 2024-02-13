package termicafueguina.ecommerce.configuraciones;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@CrossOrigin
@EnableWebSecurity
@Configuration
public class AutorizacionWeb {
    @Bean
    protected SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        //Cross-Origin Resource Sharing
        //http.cors().and()
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/api/logout", "/api/login").permitAll()
                .antMatchers("/rest", "/rest/**").permitAll()



        return http.build();
    }
}
