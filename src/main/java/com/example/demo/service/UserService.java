package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	 @Autowired
	    private UserRepository userRepository;
	 @Autowired
	 private EmailService emailService;

	    public User login(String login, String password) {
	    	return userRepository.findByLoginAndPassword(login, password);
	    }
	    
	    public String loginCheck(HttpSession session,User user,Model model) {
	    	  if (user != null) {
		            session.setAttribute("loggedInUser", user);
		            switch (user.getRole()) {
		                case "ADMIN":
		                	session.setAttribute("user", user);
		                	model.addAttribute("user",user);
		                    return "admin/dashboard";
		                case "MANAGER":
		                	model.addAttribute("user",user);
		                	 session.setAttribute("managerId", user.getId());
		                	 session.setAttribute("user", user);
		                    return "manager/dashboard";
		                case "EMPLOYEE":
		                	session.setAttribute("user", user);
		                	model.addAttribute("user",user);
		                    return "employee/dashboard";
		                default:
		                    model.addAttribute("error", "Invalid Role!");
		                    return "login";
		            }
		        } else {
		            model.addAttribute("error", "Invalid Username or Password!");
		            return "login";
		        }
	    }
		public void saveUser(User user) {
			if (user.getRole().equalsIgnoreCase("employee")) {
				user.setResource("Bench");
			}
			userRepository.save(user);	
		}
		public String getUsers(Model model) {
			 List<User> users = userRepository.findAll();  
		        model.addAttribute("users", users);  
		        return "user/UserList";
		}
		
		public String getUserEditForm(Long id,Model model) {
			 User user = userRepository.findById(id).get();
		     model.addAttribute("user", user);
		     return "/user/EditUser";  // JSP page name
		}
		
		public String updateUser(User user) {
			userRepository.save(user);  // JPA will update if ID exists
		     return "redirect:/users";   // Redirect back to list
		}
		
		public String deleteUser(Long id) {
			 userRepository.deleteById(id);
		        return "redirect:/users";
		}
		
		//for task
		 public User getUserById(Long id) {
		        return userRepository.findById(id)
		                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
		    }
		 
		 
		 public List<User> getEmpByResource() {
			 return userRepository.findByResource();
		 }
		 
		 //forgot password
		 public boolean sendPasswordByEmail(String email) {
		        Optional<User> userOpt = userRepository.findByEmail(email);

		        if (userOpt.isPresent()) {
		            User user = userOpt.get();

		            String subject = "Your Account Password";
		            String body = "Hi " + user.getName() + ",\n\n" +
		                          "Your password is: " + user.getPassword() + "\n\n" +
		                          "Please keep it safe.";

		            emailService.sendEmail(user.getEmail(), subject, body);
		            return true;
		        } else {
		            return false;
		        }
		    }
}
