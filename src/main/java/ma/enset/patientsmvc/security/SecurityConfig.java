package ma.enset.patientsmvc.security;

import ma.enset.patientsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

 //   @Bean
 //  public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder){

//       // return new InMemoryUserDetailsManager(
//        //        User.withUsername("user1").password(encodedPassword).roles("USER").build(),
//       //         User.withUsername("user2").password(encodedPassword).roles("USER").build(),
//       //         User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
//       // );
  // return new InMemoryUserDetailsManager();
//        auth.jdbcAuthentification();
    // }

    @Bean
    public UserDetailsService userDetailsService() {
        return  userDetailsService; // Injectez votre implémentation de UserDetailsService ici
    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(PasswordEncoder passwordEncoder) {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery("select username as principal, password as credentials, active as enabled from users where username = ?");
//        userDetailsManager.setAuthoritiesByUsernameQuery("select username as principal, role as role from users_roles where username = ?");
//        userDetailsManager.setRolePrefix("ROLE_");
//        //userDetailsManager.setPasswordEncoder(passwordEncoder);
//        return userDetailsManager;
//    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(ar -> ar
                        .requestMatchers("/delete/**", "/edit/**", "/save/**", "/formPatients/**").hasRole("ADMIN")
                        .requestMatchers("/index/**").hasAuthority("USER")
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/user/**").hasAuthority("USER")
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/webjars/***").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(customizer -> customizer.accessDeniedPage("/403"))
                .build();
    }

}