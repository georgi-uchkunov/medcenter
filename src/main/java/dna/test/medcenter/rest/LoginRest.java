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

/**
 * RestController, which uses functionalities from the {@link UserRepository}
 * and {@link RoleRepository} related to their respective entities. Receives
 * AJAX requests from the client, directed based on GetMapping and PostMapping.
 * 
 * Handles login, logout and user access based on {@link Role}
 */
@RestController
public class LoginRest {

	private UserRepository userRepository;
	private RoleRepository roleRepository;

	@Autowired
	public LoginRest(final UserRepository userRepository, final RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	/**
	 * Redirects users to their respective Home pages (patient or medical
	 * physician), depending on their {@link Role}
	 * 
	 * @param username, password, session
	 * @return link to either the patient or medical physician home page
	 */
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

	/**
	 * Redirects users back to the landing page and invalidates the ongoing session
	 * 
	 * @param session
	 * @return link to the landing page
	 */
	@PostMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/";
	}

	/**
	 * Redirects {@link User} with the "PATIENT" {@link Role} back to landing page
	 * and invalidates the ongoing session in case they try to access a page meant
	 * for the "MED_PHYS" {@link Role}
	 * 
	 * @param session
	 * @return either an "ACCEPTED" string or a link to the landing page
	 */
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

	/**
	 * Returns the username of the {@link User} for the ongoing session
	 * 
	 * @param session
	 * @return the username of the logged in user
	 */
	@GetMapping(value = "/getCurrentUsername")
	public String getCurrentUsername(HttpSession session) {
		final User currentUser = (User) session.getAttribute("currentUser");
		String username = currentUser.getUsername();
		return username;
	}

}