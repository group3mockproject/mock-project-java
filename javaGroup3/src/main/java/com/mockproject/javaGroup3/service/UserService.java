package com.mockproject.javaGroup3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mockproject.javaGroup3.model.User;
import com.mockproject.javaGroup3.respository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder; // Tiêm PasswordEncoder qua @Autowired

    // Tạo mới user
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu khi tạo mới
        return userRepository.save(user);
    }

    // Lấy tất cả users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy user theo ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Cập nhật user
    public User updateUser(Integer id, User userDetails) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            
            // Chỉ cập nhật các trường nếu giá trị không phải là null
            if (userDetails.getFirstname() != null) {
                user.setFirstname(userDetails.getFirstname());
            }
            if (userDetails.getLastname() != null) {
                user.setLastname(userDetails.getLastname());
            }
            if (userDetails.getEmail() != null) {
                user.setEmail(userDetails.getEmail());
            }
            if (userDetails.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            if (userDetails.getRole() != null) {
                user.setRole(userDetails.getRole());
            }

            return userRepository.save(user); // Lưu lại người dùng với các trường đã cập nhật
        } else {
            return null; // Người dùng không tồn tại
        }
    }

    // Xóa user
    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

}
