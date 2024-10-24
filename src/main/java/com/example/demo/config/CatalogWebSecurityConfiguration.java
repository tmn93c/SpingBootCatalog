package com.example.demo.config;

import com.example.demo.anotation.EnableSelectCaching;
import com.example.demo.security.JwtAuthenticationEntryPoint;
import com.example.demo.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
@EnableSelectCaching
@EnableMethodSecurity
public class CatalogWebSecurityConfiguration {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final UserDetailsService userDetailsService;

    public CatalogWebSecurityConfiguration(JwtAuthenticationEntryPoint unauthorizedHandler, UserDetailsService userDetailsService) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {}

    @Bean
    AuthoritiesConverter realmRolesAuthoritiesConverter() {
        return claims -> {
            final var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
            final var roles =
                    realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
            return roles.map(List::stream).orElse(Stream.empty()).map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast).toList();
        };
    }

    @Bean
    GrantedAuthoritiesMapper authenticationConverter(
            Converter<Map<String, Object>, Collection<GrantedAuthority>> realmRolesAuthoritiesConverter) {
        return (authorities) -> authorities.stream()
                .filter(authority -> authority instanceof OidcUserAuthority)
                .map(OidcUserAuthority.class::cast).map(OidcUserAuthority::getIdToken)
                .map(OidcIdToken::getClaims).map(realmRolesAuthoritiesConverter::convert)
                .flatMap(roles -> roles.stream()).collect(Collectors.toSet());
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http,  ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of(CorsConfiguration.ALL));
                    configuration.setAllowedMethods(List.of(CorsConfiguration.ALL));
                    configuration.setAllowedHeaders(List.of(CorsConfiguration.ALL));
                    return configuration;
                }))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/hello"
                                , "/favicon.ico"
                                , "/api/auth/**"
                                , "/actuator/**"
                                , "/swagger-ui/**"
                                , "/api-docs/**"
                                , "/csv"
                        ).permitAll()
                        .requestMatchers("/me")
                        .authenticated())
                .httpBasic(Customizer.withDefaults());

//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService);
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();
//
//        http.exceptionHandling(
//                ex -> ex.authenticationEntryPoint(unauthorizedHandler))
//                .authenticationManager(authenticationManager)
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        // Add our custom JWT security filter
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.oauth2Login(Customizer.withDefaults());
        http.logout((logout) -> {
            var logoutSuccessHandler =
                    new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
            logoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/");
            logout.logoutSuccessHandler(logoutSuccessHandler);
        });

        return http.build();
    }
}
