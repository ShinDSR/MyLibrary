package com.library.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.library.library.models.entities.Kategori;
import com.library.library.models.repos.KategoriRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class KategoriService {

    @Autowired
    private KategoriRepo kategoriRepo;

    public Kategori save(Kategori kategori) {
        return kategoriRepo.save(kategori);
    }

    public Kategori findOne(long id) {
        Optional<Kategori> kategori = kategoriRepo.findById(id);
        if (!kategori.isPresent()) {
            return null;
        }
        return kategori.get();
    }

    public Iterable<Kategori> findAll() {
        return kategoriRepo.findAll();
    }

    public void removeOne(long id) {
        kategoriRepo.deleteById(id);
    }

    public Iterable<Kategori> findByName(String nama, Pageable pageable) {
        return kategoriRepo.findByNamaContains(nama, pageable);
    }
}
