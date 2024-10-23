package com.mockproject.javaGroup3.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockproject.javaGroup3.dto.UserDTO;
import com.mockproject.javaGroup3.model.User;
import com.mockproject.javaGroup3.respository.UserRepository;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	// Bean để mã hóa mật khẩu
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Autowired
	private UserRepository userRepository;
	// Bean để cấu hình chuỗi bộ lọc bảo mật
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors() // Kích hoạt CORS
				.and().csrf(
						csrf -> csrf.ignoringRequestMatchers("/api/**"))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers("/admin/**").hasRole("ADMIN") // Chỉ Admin
																											// mới có
																											// quyền
																											// truy cập
						.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // Cả User và Admin đều truy cập được
						.requestMatchers("/api/public/**", "/api/v1/login", "/api/register", "/", "/webjars/**",
								"/css/**", "/api/**")
						.permitAll().anyRequest().authenticated() // Tất cả các yêu cầu khác cần xác thực
				)

				.formLogin(form -> form.loginProcessingUrl("/api/v1/login") // URL xử lý đăng nhập (POST)
						.successHandler((request, response, authentication) -> {
							Map<String, Object> responseBody = new HashMap<>();
							responseBody.put("message", "Đăng nhập thành công");
							responseBody.put("success", "Đăng nhập thành công");
							// Lấy thông tin người dùng từ authentication
							UserDetails userDetails = (UserDetails) authentication.getPrincipal(); // Lấy thông tin
							User user = userRepository.findByUsername(userDetails.getUsername());													// người dùng
							responseBody.put("user", new UserDTO(user)); // Chuyển đổi User sang UserDTO

							// Ghi dữ liệu vào response
							response.setContentType("application/json");
							response.setStatus(HttpServletResponse.SC_OK); // Trả về trạng thái 200
							response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody)); // Ghi vào
																												// response
						}).failureHandler((request, response, exception) -> {
							response.setContentType("application/json");
							response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
							response.getWriter().write("{\"message\": \"Invalid username or password%%\"}");
						}));
		return http.build();
	}

	// Bean để cấu hình AuthenticationManager
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
}
