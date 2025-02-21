package com.library.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library.models.entities.Buku;
import com.library.library.models.repos.BukuRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BukuService {

    @Autowired
    private BukuRepo bukuRepo;

    public Buku save(Buku buku) {
        return bukuRepo.save(buku);
    }

    public Buku findOne(long id) {
        Optional<Buku> buku = bukuRepo.findById(id);
        if (!buku.isPresent()) {
            return null;
        }
        return bukuRepo.findById(id).get();
    }

    public Iterable<Buku> findAll() {
        return bukuRepo.findAll();
    }

    public void removeOne(long id) {
        bukuRepo.deleteById(id);
    }
}
