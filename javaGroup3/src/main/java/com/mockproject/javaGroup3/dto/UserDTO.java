package com.mockproject.javaGroup3.dto;

import com.mockproject.javaGroup3.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Tự động tạo getter, setter, toString, equals, và hashCode
@NoArgsConstructor // Tạo constructor không tham số
@AllArgsConstructor // Tạo constructor có tham số cho tất cả các trường
public class UserDTO {
    private String username;
    private String email; // hoặc các thuộc tính khác mà bạn muốn trả về
    private String role;

    // Constructor nhận đối tượng User từ mô hình của bạn
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
