package com.mqgroup24.lectura360.controller;

import com.mqgroup24.lectura360.dao.NewUserDTO;
import com.mqgroup24.lectura360.dao.UserCredentialsDTO;
import com.mqgroup24.lectura360.entity.User;
import com.mqgroup24.lectura360.mapper.UserMapper;
import com.mqgroup24.lectura360.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserCredentialsDTO userCredentialsDTO, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = userService.findUserByCrendentials(userCredentialsDTO.getUsernameOrEmail(), userCredentialsDTO.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "¡Credenciales incorrectas!");
            return "redirect:login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute NewUserDTO user, Model model) {

        if (userService.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("error", "Usuario ya existente");
            return "signup";
        }

        if (userService.findUserByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email ya existente");
            return "signup";
        }

        if (user.getPassword().length() < 8) {
            model.addAttribute("error", "La contraseña debe tener al menos 8 caracteres");
            return "signup";
        }

        userService.createUser(UserMapper.toEntity(user));
        return "redirect:login";
    }

}
