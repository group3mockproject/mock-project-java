package proj.mockproj.apartment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Bean để mã hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean để cấu hình chuỗi bộ lọc bảo mật
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**") // Bỏ qua CSRF cho các yêu cầu đến /api/**
            )
            .authorizeHttpRequests(authorize -> authorize
	    		 .requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ Admin mới có quyền truy cập
	             .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Cả User và Admin đều truy cập được
	             .requestMatchers("/public/**", "/login", "/register","/","/webjars/**","/css/**","/api/**").permitAll()
	             .anyRequest().authenticated() // Tất cả các yêu cầu khác cần xác thực
            )
            .formLogin(form -> form
                .loginPage("/login") // Chỉ định trang đăng nhập
                .loginProcessingUrl("/login") // URL xử lý đăng nhập (POST)
                .defaultSuccessUrl("/home", true) // Trang mặc định sau khi đăng nhập thành công
                .permitAll() // Cho phép tất cả truy cập đến trang đăng nhập
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // URL để thực hiện đăng xuất
                .logoutSuccessUrl("/login?logout") // URL chuyển hướng sau khi đăng xuất thành công
                .permitAll() // Cho phép tất cả truy cập đến URL đăng xuất
            );
        return http.build();
    }

    // Bean để cấu hình AuthenticationManager
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
