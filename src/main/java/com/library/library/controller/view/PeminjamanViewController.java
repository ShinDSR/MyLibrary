package com.library.library.controller.view;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library.library.dto.PeminjamanDto;
import com.library.library.models.entities.Buku;
import com.library.library.models.entities.Peminjaman;
import com.library.library.models.entities.User;
import com.library.library.services.BukuService;
import com.library.library.services.PeminjamanService;
import com.library.library.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/peminjamans")
public class PeminjamanViewController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BukuService bukuService;

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        Iterable<Peminjaman> peminjamans = peminjamanService.findAll();
        model.addAttribute("peminjamans", peminjamans);
        model.addAttribute("pageTitle", "Peminjaman");
        model.addAttribute("showSearch", true);
        return "peminjamans/index";
    }

    @GetMapping("/create")
    public String show(Model model) {
        PeminjamanDto peminjamanDto = new PeminjamanDto();
        model.addAttribute("peminjamanDto", peminjamanDto);
        model.addAttribute("bukus", bukuService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("pageTitle", "Tambah Peminjaman");
        model.addAttribute("showSearch", false);
        return "peminjamans/create";
    }

    @PostMapping("/create")
    public String create(@Valid PeminjamanDto peminjamanDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "peminjamans/create";
        }

        Buku buku = bukuService.findOne(peminjamanDto.getBuku().getId());
        if (buku == null) {
            return "peminjamans/create";
        }

        User user = userService.findOne(peminjamanDto.getUser().getId());
        if (user == null) {
            return "peminjamans/create";
        }

        Peminjaman peminjaman = modelMapper.map(peminjamanDto, Peminjaman.class);
        peminjaman.setBuku(buku);
        peminjaman.setUser(user);
        peminjamanService.save(peminjaman);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Peminjaman created successfully");
        return "redirect:/peminjamans";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        Peminjaman peminjaman = peminjamanService.findOne(id);
        if (peminjaman == null) {
            return "redirect:/peminjamans";
        }

        PeminjamanDto peminjamanDto = modelMapper.map(peminjaman, PeminjamanDto.class);
        model.addAttribute("id", id);
        model.addAttribute("peminjamanDto", peminjamanDto);
        model.addAttribute("bukus", bukuService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("pageTitle", "Edit Peminjaman");
        model.addAttribute("showSearch", false);
        return "peminjamans/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, @Valid PeminjamanDto peminjamanDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "peminjamans/edit";
        }

        Peminjaman peminjaman = peminjamanService.findOne(id);
        if (peminjaman == null) {
            return "redirect:/peminjamans";
        }

        Buku buku = bukuService.findOne(peminjamanDto.getBuku().getId());
        if (buku == null) {
            return "peminjamans/edit";
        }

        User user = userService.findOne(peminjamanDto.getUser().getId());
        if (user == null) {
            return "peminjamans/edit";
        }

        modelMapper.map(peminjamanDto, peminjaman);
        peminjaman.setBuku(buku);
        peminjaman.setUser(user);
        peminjamanService.save(peminjaman);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Peminjaman updated successfully");
        return "redirect:/peminjamans";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Peminjaman peminjaman = peminjamanService.findOne(id);
        if (peminjaman == null) {
            return "redirect:/peminjamans";
        }

        peminjamanService.removeOne(id);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Peminjaman deleted successfully");
        return "redirect:/peminjamans";
    }

}
