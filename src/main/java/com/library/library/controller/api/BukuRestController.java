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

import com.library.library.dto.BukuDto;
import com.library.library.dto.ResponseData;
import com.library.library.models.entities.Buku;
import com.library.library.models.entities.Kategori;
import com.library.library.services.BukuService;
import com.library.library.services.KategoriService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/buku")
public class BukuRestController {

    @Autowired
    private BukuService bukuService;

    @Autowired
    private KategoriService kategoriService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Buku>> create(@Valid @RequestBody BukuDto bukuDto, Errors errors) {
        ResponseData<Buku> responseData = new ResponseData<>();
        
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Kategori kategori = kategoriService.findOne(bukuDto.getKategori());
        if (kategori == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Kategori tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Buku buku = modelMapper.map(bukuDto, Buku.class);
        buku.setKategori(kategori);

        responseData.setStatus(true);
        responseData.setPayload(bukuService.save(buku));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Buku> findAll() {
        return bukuService.findAll();
    }

    @GetMapping("/{id}")
    public Buku findOne(@PathVariable("id") Long id) {
        return bukuService.findOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Buku>> update(@Valid @PathVariable("id") Long id, @RequestBody BukuDto bukuDto, Errors errors) {
        ResponseData<Buku> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Buku buku = bukuService.findOne(id);
        if (buku == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Buku tidak ditemukan");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Kategori kategori = kategoriService.findOne(bukuDto.getKategori());
        if (kategori == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Kategori tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        modelMapper.map(bukuDto, buku);
        buku.setKategori(kategori);

        responseData.setStatus(true);
        responseData.setPayload(bukuService.save(buku));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        bukuService.removeOne(id);
    }
    
}
