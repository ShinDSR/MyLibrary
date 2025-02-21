package com.library.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library.models.entities.Peminjaman;
import com.library.library.models.repos.PeminjamanRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PeminjamanService {

    @Autowired
    private PeminjamanRepo peminjamanRepo;

    public Peminjaman save(Peminjaman peminjaman) {
        return peminjamanRepo.save(peminjaman);
    }

    public Peminjaman findOne(long id) {
        Optional<Peminjaman> peminjaman = peminjamanRepo.findById(id);
        if (!peminjaman.isPresent()) {
            return null;
        }
        return peminjamanRepo.findById(id).get();
    }

    public Iterable<Peminjaman> findAll() {
        return peminjamanRepo.findAll();
    }

    public void removeOne(long id) {
        peminjamanRepo.deleteById(id);
    }
}
