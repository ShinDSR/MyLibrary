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

import com.library.library.dto.PeminjamanDto;
import com.library.library.dto.ResponseData;
import com.library.library.models.entities.Buku;
import com.library.library.models.entities.Peminjaman;
import com.library.library.models.entities.User;
import com.library.library.services.BukuService;
import com.library.library.services.PeminjamanService;
import com.library.library.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/peminjaman")
public class PeminjamanRestController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private BukuService bukuService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Peminjaman>> create(@Valid @RequestBody PeminjamanDto peminjamanDto, Errors errors) {
        ResponseData<Peminjaman> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Buku buku = bukuService.findOne(peminjamanDto.getBuku());
        if (buku == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Buku tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        User user = userService.findOne(peminjamanDto.getUser());
        if (user == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("User tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Peminjaman peminjaman = modelMapper.map(peminjamanDto, Peminjaman.class);
        peminjaman.setBuku(buku);
        peminjaman.setUser(user);

        responseData.setStatus(true);
        responseData.setPayload(peminjamanService.save(peminjaman));
        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public Iterable<Peminjaman> findAll() {
        return peminjamanService.findAll();
    }

    @GetMapping("/{id}")
    public Peminjaman findOne(@PathVariable("id") Long id) {
        return peminjamanService.findOne(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<Peminjaman>> update(@Valid @PathVariable("id") Long id, @RequestBody PeminjamanDto peminjamanDto, Errors errors) {
        ResponseData<Peminjaman> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Peminjaman peminjaman = peminjamanService.findOne(id);
        if (peminjaman == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Peminjaman not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }

        Buku buku = bukuService.findOne(peminjamanDto.getBuku());
        if (buku == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("Buku tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        User user = userService.findOne(peminjamanDto.getUser());
        if (user == null) {
            responseData.setStatus(false);
            responseData.getMessages().add("User tidak ditemukan");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        modelMapper.map(peminjamanDto, peminjaman);
        peminjaman.setBuku(buku);
        peminjaman.setUser(user);

        responseData.setStatus(true);
        responseData.setPayload(peminjamanService.save(peminjaman));
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        peminjamanService.removeOne(id);
    }

}
