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

import com.library.library.dto.BukuDto;
import com.library.library.models.entities.Buku;
import com.library.library.models.entities.Kategori;
import com.library.library.services.BukuService;
import com.library.library.services.KategoriService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/bukus")
public class BukuViewController {

    @Autowired
    private BukuService bukuService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private KategoriService kategoriService;

    @GetMapping({"", "/"})
    public String index(Model model) {
        Iterable<Buku> bukus = bukuService.findAll();
        model.addAttribute("bukus", bukus);
        model.addAttribute("pageTitle", "Buku");
        model.addAttribute("showSearch", true);
        return "bukus/index";
    }
    
    @GetMapping("/create")
    public String show(Model model) {
        BukuDto bukuDto = new BukuDto();
        model.addAttribute("bukuDto", bukuDto);
        model.addAttribute("kategoris", kategoriService.findAll());
        model.addAttribute("pageTitle", "Tambah Buku");
        model.addAttribute("showSearch", false);
        return "bukus/create";
    }

    @PostMapping("/create")
    public String create(@Valid BukuDto bukuDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bukus/create";
        }

        Kategori kategori = kategoriService.findOne(bukuDto.getKategori().getId());
        if (kategori == null) {
            return "bukus/create";
        }

        Buku buku = modelMapper.map(bukuDto, Buku.class);
        buku.setKategori(kategori);
        bukuService.save(buku);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Book created successfully");
        return "redirect:/bukus";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes) {
        Buku buku = bukuService.findOne(id);
        if (buku == null) {
            return "redirect:/bukus";
        }

        BukuDto bukuDto = modelMapper.map(buku, BukuDto.class);
        model.addAttribute("id", id);
        model.addAttribute("bukuDto", bukuDto);
        model.addAttribute("kategoris", kategoriService.findAll());
        model.addAttribute("pageTitle", "Edit Buku");
        model.addAttribute("showSearch", false);
        return "bukus/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") long id, @Valid @ModelAttribute BukuDto bukuDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "bukus/edit";
        }

        Buku buku = bukuService.findOne(id);
        if (buku == null) {
            redirectAttributes.addFlashAttribute("status", "Error");
            redirectAttributes.addFlashAttribute("messages", "Book not found");
            return "redirect:/bukus";
        }

        Kategori kategori = kategoriService.findOne(bukuDto.getKategori().getId());
        if (kategori == null) {
            return "bukus/edit";
        }

        modelMapper.map(bukuDto, buku);
        buku.setKategori(kategori);
        bukuService.save(buku);

        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Book updated successfully");
        return "redirect:/bukus";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        Buku buku = bukuService.findOne(id);
        if (buku == null) {
            return "redirect:/bukus";
        }

        bukuService.removeOne(id);
        redirectAttributes.addFlashAttribute("status", "Success");
        redirectAttributes.addFlashAttribute("messages", "Book deleted successfully");
        return "redirect:/bukus";
    }
    

    

}
