package com.library.library.controller.view;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.library.dto.UserDto;
import com.library.library.models.entities.User;
import com.library.library.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserViewController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping ({"", "/"})
    public String index(Model model) {
        Iterable<User> users = userService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("pageTitle", "User");
        model.addAttribute("showSearch", true);
        return "users/index";
    }

    @GetMapping("/create")
    public String show(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        model.addAttribute("pageTitle", "Tambah User");
        model.addAttribute("showSearch", false);
        return "users/create";
    }

    @PostMapping("/create")
    public String create(@Valid UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "users/create";
        }

        User user = modelMapper.map(userDto, User.class);
        userService.save(user);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "User created successfully");
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        User user = userService.findOne(id);

        if (user == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "User not found");
            return "redirect:/users";
        }

        UserDto userDto = modelMapper.map(user, UserDto.class);
        model.addAttribute("id", id);
        model.addAttribute("userDto", userDto);
        model.addAttribute("pageTitle", "Edit User");
        model.addAttribute("showSearch", false);
        return "users/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }

        User user = userService.findOne(id);
        if (user == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "User not found");
            return "redirect:/users";
        }

        modelMapper.map(userDto, user);
        userService.save(user);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "User updated successfully");
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        User user = userService.findOne(id);

        if (user == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "User not found");
            return "redirect:/users";
        }

        userService.removeOne(id);
        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "User deleted successfully");
        return "redirect:/users";
    }

}
