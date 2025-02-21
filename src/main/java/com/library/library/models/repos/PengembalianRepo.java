package com.library.library.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.entities.Pengembalian;

public interface PengembalianRepo extends JpaRepository<Pengembalian, Long> {

}
