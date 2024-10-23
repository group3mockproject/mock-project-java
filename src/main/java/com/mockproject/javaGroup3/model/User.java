package com.mockproject.javaGroup3.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;  // ID tự tăng

    @Column(nullable = false, length = 100, unique = true)
    private String username; // Tên đăng nhập

    @Column(nullable = false, length = 100, unique = true)
    private String email;    // Email duy nhất

    @Column(nullable = false, length = 255)
    private String password; // Mật khẩu đã mã hóa

    @Column(nullable = false, length = 50)
    private String role;     // Vai trò (admin, resident, staff, ...)

    @Column(length = 50, columnDefinition = "varchar(50) default 'active'")
    private String status;   // Trạng thái tài khoản (active, inactive)

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt; // Thời gian tạo tài khoản

    @UpdateTimestamp
    private LocalDateTime updatedAt; // Thời gian cập nhật lần cuối

    @Column(nullable = true, columnDefinition = "bit default 0")
    private Boolean delflag; // Cờ xóa (1 = đã xóa, 0 = chưa xóa)

    // Phương thức trả về authorities
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}