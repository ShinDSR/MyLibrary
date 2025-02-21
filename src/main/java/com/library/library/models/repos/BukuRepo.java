package com.library.library.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.entities.Buku;

public interface BukuRepo extends JpaRepository<Buku, Long> {

}
