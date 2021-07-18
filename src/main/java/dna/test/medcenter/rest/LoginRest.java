package dna.test.medcenter.rest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dna.test.medcenter.models.Role;
import dna.test.medcenter.models.User;
import dna.test.medcenter.repos.RoleRepository;
import dna.test.medcenter.repos.UserRepository;

@RestController
public class LoginRest {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public LoginRest(final UserRepository userRepository, final RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password, HttpSession session) {
		final User currentUser = userRepository.findByUsernameAndPassword(username, password);
		Role medPhysRole = roleRepository.findByCode("MED_PHYS");
		if (null != currentUser) {
			session.setAttribute("currentUser", currentUser);
			if (currentUser.getRoles().contains(medPhysRole)) {
				return "home_physician";
			} else {
				return "home_patient";
			}
		}
		return "/";
	}

	@PostMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/";
	}

	@GetMapping(value = "/access_control_patient")
	public String preventPatientAccess(HttpSession session) {
		final User currentUser = (User) session.getAttribute("currentUser");
		Role medPhysRole = roleRepository.findByCode("MED_PHYS");

		if (currentUser != null && currentUser.getRoles().contains(medPhysRole)) {
			return "ACCEPTED";
		} else {
			session.invalidate();
			return "/";
		}
	}

}