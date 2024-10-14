package proj.mockproj.apartment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proj.mockproj.apartment.responsitory.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Tìm User từ cơ sở dữ liệu
//        org.o7planning.project.model.User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found: " + username);
//        }
		if ("sa".equals(username)) {
			// Tạo một đối tượng User với mật khẩu là "sa" và quyền hạn là "USER"
			return User.withUsername("sa").password("{noop}sa") // {noop} có nghĩa là không cần mã hóa mật khẩu
					.roles("USER").build();
		} else {
			throw new UsernameNotFoundException("User not found");
		}
		// Chuyển đổi từ org.o7planning.project.model.User sang
		// org.springframework.security.core.userdetails.User
//        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles("USER") // Tùy chỉnh vai trò người dùng
//                .build();

	}
}
