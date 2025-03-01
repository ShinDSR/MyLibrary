package com.library.library.services;

import java.util.List;
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

    public List<Buku> findByJudul(String judul) {
        return bukuRepo.findByJudulContains(judul);
    }

    public List<Buku> findByPenerbit(String penerbit) {
        return bukuRepo.findByPenerbitContains(penerbit);
    }

    public List<Buku> findByPengarang(String pengarang) {
        return bukuRepo.findByPengarangContains(pengarang);
    }

    public List<Buku> findByTahunTerbit(int tahunTerbit) {
        return bukuRepo.findByTahunTerbit(tahunTerbit);
    }

    public List<Buku> findByKategori(String kategori) {
        return bukuRepo.findByKategori_NamaContains(kategori);
    }
    
}
