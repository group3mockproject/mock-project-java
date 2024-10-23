package com.mockproject.javaGroup3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mockproject.javaGroup3.model.User;
import com.mockproject.javaGroup3.respository.UserRepository;

@RestController // Thay đổi từ @Controller sang @RestController
@RequestMapping("/api/v1") // Thêm prefix cho các endpoint của bạn
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User()); // Đảm bảo User là một lớp đã được định nghĩa
		return "register";
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user) {
		// Kiểm tra xem người dùng đã tồn tại chưa
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
		}
		System.out.println("Right Here");
		// Mã hóa mật khẩu
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		// Trả về phản hồi thành công
		return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		try {
			// Lấy người dùng từ cơ sở dữ liệu
			User user = userRepository.findByUsername(username); // Tùy chỉnh theo phương thức của bạn
			if (user == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password^^^^");
			}
			
			// So sánh mật khẩu đã mã hóa
			if (!passwordEncoder.matches(password, user.getPassword())) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password^^^^");
			}

			// Nếu đăng nhập thành công
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
			return ResponseEntity.ok().body("Đăng nhập thành công"); // Trả về thông báo thành công
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password^^^^^Eror"); // Trả về thông
																										// báo lỗi
		}
	}

	@GetMapping({ "/home", "/" })
	public String home() {
		return "home";
	}
}