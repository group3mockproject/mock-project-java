package com.mockproject.javaGroup3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mockproject.javaGroup3.model.User;
import com.mockproject.javaGroup3.respository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Tạo mới user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Lấy tất cả users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy user theo ID
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Cập nhật user
    public User updateUser(Long userId, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setStatus(userDetails.getStatus());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    // Xóa user
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    // Tải user từ database theo username (phục vụ cho quá trình xác thực)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		 Tìm User từ cơ sở dữ liệu
       User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
//  		 Chuyển đổi từ org.o7planning.project.model.User sang
//  		 org.springframework.security.core.userdetails.User
          return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                  .password(user.getPassword())
                  .roles(user.getRole()) // Tùy chỉnh vai trò người dùng
                  .build();

  	}

}
