package com.library.library.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.entities.Peminjaman;

public interface PeminjamanRepo extends JpaRepository<Peminjaman, Long> {

}
