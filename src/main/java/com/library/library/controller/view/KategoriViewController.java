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

import com.library.library.dto.KategoriDto;
import com.library.library.models.entities.Kategori;
import com.library.library.services.KategoriService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/kategoris")
public class KategoriViewController {

    @Autowired
    private KategoriService kategoriService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping({"", "/"})
    public String index(Model model) {
        Iterable<Kategori> kategoris = kategoriService.findAll();
        model.addAttribute("kategoris", kategoris);
        model.addAttribute("pageTitle", "Kategori");
        model.addAttribute("showSearch", true);
        return "kategoris/index";
    }

    @GetMapping("/create")
    public String show(Model model) {
        KategoriDto kategoriDto = new KategoriDto();
        model.addAttribute("kategoriDto", kategoriDto);
        model.addAttribute("pageTitle", "Tambah Kategori");
        model.addAttribute("showSearch", false);
        return "kategoris/create";
    }

    @PostMapping("/create")
    public String create(@Valid KategoriDto kategoriDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "kategoris/create";
        }

        Kategori kategori = modelMapper.map(kategoriDto, Kategori.class);
        kategoriService.save(kategori);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Category created successfully");
        return "redirect:/kategoris";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        Kategori kategori = kategoriService.findOne(id);
        if (kategori == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "Category not found");
            return "redirect:/kategori";
        }
        KategoriDto kategoriDto = modelMapper.map(kategori, KategoriDto.class);
        model.addAttribute("kategoriDto", kategoriDto);
        model.addAttribute("id", id);
        model.addAttribute("pageTitle", "Edit Kategori");
        model.addAttribute("showSearch", false);
        return "kategoris/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, @Valid @ModelAttribute KategoriDto kategoriDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "kategoris/edit";
        }

        Kategori kategori = kategoriService.findOne(id);
        if (kategori == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "Category not found");
            return "redirect:/kategoris";
        }

        // Menggunakan ModelMapper tetapi tetap mempertahankan ID
        modelMapper.map(kategoriDto, kategori);
        kategoriService.save(kategori);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Category updated successfully");
        return "redirect:/kategoris";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Kategori kategori = kategoriService.findOne(id);
        if (kategori == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "Category not found");
        } else {
            kategoriService.removeOne(id);
            redirectAttributes.addFlashAttribute("status", "Success");
            redirectAttributes.addFlashAttribute("messages", "Category deleted successfully");
        }
        return "redirect:/kategoris";
    }

}
