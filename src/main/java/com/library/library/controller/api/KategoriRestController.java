package com.library.library.controller.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.library.dto.KategoriDto;
import com.library.library.dto.ResponseData;
import com.library.library.models.entities.Kategori;
import com.library.library.services.KategoriService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/kategori")
public class KategoriRestController {

    @Autowired
    private KategoriService kategoriService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Kategori>> create(@Valid @RequestBody KategoriDto kategoriDto, Errors errors) {
        ResponseData<Kategori> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Kategori kategori = modelMapper.map(kategoriDto, Kategori.class);
        responseData.setStatus(true);
        responseData.setPayload(kategoriService.save(kategori));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Kategori> findAll() {
        return kategoriService.findAll();
    }

    @GetMapping("/{id}")
    public Kategori findOne(@PathVariable("id") Long id) {
        return kategoriService.findOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Kategori>> update(@Valid @PathVariable("id") Long id, @RequestBody KategoriDto kategoriDto, Errors errors) {
        ResponseData<Kategori> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Kategori kategori = kategoriService.findOne(id);
        if (kategori == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Kategori not found");
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        modelMapper.map(kategoriDto, kategori);
        responseData.setStatus(true);
        responseData.setPayload(kategoriService.save(kategori));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        kategoriService.removeOne(id);
    }
}
