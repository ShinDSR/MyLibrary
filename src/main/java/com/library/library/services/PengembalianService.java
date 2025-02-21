package com.library.library.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.library.models.entities.Pengembalian;
import com.library.library.models.repos.PengembalianRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PengembalianService {

    @Autowired
    private PengembalianRepo pengembalianRepo;

    public Pengembalian save(Pengembalian pengembalian) {
        return pengembalianRepo.save(pengembalian);
    }

    public Pengembalian findOne(long id) {
        Optional<Pengembalian> pengembalian = pengembalianRepo.findById(id);
        if (!pengembalian.isPresent()) {
            return null;
        }
        return pengembalianRepo.findById(id).get();
    }

    public Iterable<Pengembalian> findAll() {
        return pengembalianRepo.findAll();
    }

    public void removeOne(long id) {
        pengembalianRepo.deleteById(id);
    }

}
