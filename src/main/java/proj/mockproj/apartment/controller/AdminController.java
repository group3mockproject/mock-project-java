package proj.mockproj.apartment.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
	 	@PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/admin/dashboard")
	    public String adminDashboard() {
	        return "This is the admin dashboard.";
	    }
	 		
	    @PreAuthorize("hasRole('USER')")
	    @GetMapping("/user/profile")
	    public String userProfile() {
	        return "This is the user profile.";
	    }
}
