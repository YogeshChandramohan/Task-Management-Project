package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
    private UserService userService;

	 @GetMapping("/login")
	    public String loginPage() {
	        return "login"; 
	    }
	 @PostMapping("/login")
	    public String login(@RequestParam String username,
	                        @RequestParam String password,
	                        HttpSession session,
	                        Model model) {

	        User user = userService.login(username, password);
	        return userService.loginCheck(session, user, model);
	    }
	 
//	 @GetMapping("/admin/dashboard")
//	 public void getDetails() {
//		 
//	 }
//	 
//	 
	 @GetMapping("/register")
	    public String showForm(Model model,User user) {
	        model.addAttribute("user", user);
	        return "user/UserRegister"; 
	 }
	 @PostMapping("/register")
	    public String saveUser(@ModelAttribute("user") User user, Model model) {
	        userService.saveUser(user);
	        model.addAttribute("message", "User registered successfully!");
	        return "redirect:/users"; 
	    }
	 @GetMapping("/logout")
	 public String logout(HttpSession session) {
	     session.invalidate();
	     return "redirect:/login";
	 }
	 
	 //Get All Users
	 @GetMapping("/users")
	    public String listUsers(Model model) {          
	        return userService.getUsers(model); 
	    }
	 
	 //Edit User
	 @GetMapping("/users/edit/{id}")
	 public String showEditForm(@PathVariable("id") Long id, Model model) {
		return userService.getUserEditForm(id, model);
	 }

	 @PostMapping("/users/update")
	 public String updateUser(@ModelAttribute("user") User user) {
		 return userService.updateUser(user);
	 }

	  // Delete user
	    @GetMapping("/users/delete/{id}")
	    public String deleteUser(@PathVariable("id") Long id) {
	    	return userService.deleteUser(id);
	    }
	 
	    //forget password
	    @GetMapping("/forgotPassword")
	    public String showForgotPasswordPage() {
	        return "user/forgot-password"; // JSP page
	    }

	    @PostMapping("/forgotPassword")
	    public String processForgotPassword(@RequestParam("email") String email, Model model) {
	        boolean status = userService.sendPasswordByEmail(email);

	        if (status) {
	            model.addAttribute("message", "Password has been sent to your email.");
	        } else {
	            model.addAttribute("error", "No account found with that email.");
	        }
	        return "user/forgot-password";  // JSP page
	    }

}
