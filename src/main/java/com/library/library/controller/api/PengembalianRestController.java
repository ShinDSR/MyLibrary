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

import com.library.library.dto.PengembalianDto;
import com.library.library.dto.ResponseData;
import com.library.library.models.entities.Peminjaman;
import com.library.library.models.entities.Pengembalian;
import com.library.library.services.PeminjamanService;
import com.library.library.services.PengembalianService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pengembalian")
public class PengembalianRestController {

    @Autowired
    private PengembalianService pengembalianService;

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Pengembalian>> create(@Valid @RequestBody PengembalianDto pengembalianDto, Errors errors) {
        ResponseData<Pengembalian> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Peminjaman peminjaman = peminjamanService.findOne(pengembalianDto.getPeminjaman().getId());
        if (peminjaman == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Peminjaman tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Pengembalian pengembalian = modelMapper.map(pengembalianDto, Pengembalian.class);
        pengembalian.setPeminjaman(peminjaman);

        responseData.setStatus(true);
        responseData.getMessages().add("Pengembalian berhasil");
        responseData.setPayload(pengembalianService.save(pengembalian));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Pengembalian> findAll() {
        return pengembalianService.findAll();
    }

    @GetMapping("/{id}")
    public Pengembalian findOne(@PathVariable("id") long id) {
        return pengembalianService.findOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Pengembalian>> update(@PathVariable("id") long id, @Valid @RequestBody PengembalianDto pengembalianDto, Errors errors) {
        ResponseData<Pengembalian> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Peminjaman peminjaman = peminjamanService.findOne(pengembalianDto.getPeminjaman().getId());
        if (peminjaman == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Peminjaman tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Pengembalian pengembalian = pengembalianService.findOne(id);
        if (pengembalian == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Pengembalian tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        modelMapper.map(pengembalianDto, pengembalian);
        pengembalian.setPeminjaman(peminjaman);

        responseData.setStatus(true);
        responseData.getMessages().add("Pengembalian berhasil diubah");
        responseData.setPayload(pengembalianService.save(pengembalian));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") long id) {
        pengembalianService.removeOne(id);
    }
}
