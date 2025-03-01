package com.library.library.models.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.entities.Kategori;

public interface KategoriRepo extends JpaRepository<Kategori, Long> {

    Page<Kategori> findByNamaContains(String nama, Pageable pageable);
}
