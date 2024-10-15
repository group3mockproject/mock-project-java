package proj.mockproj.apartment.responsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proj.mockproj.apartment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Trả về đối tượng User từ CSDL
	 Optional<User> findByUsername(String username);
}