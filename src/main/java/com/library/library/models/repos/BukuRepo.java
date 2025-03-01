package com.library.library.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.library.library.models.entities.Buku;

public interface BukuRepo extends JpaRepository<Buku, Long> {

    @EntityGraph(attributePaths = {"kategori"})
    List<Buku> findAll();

    @EntityGraph(attributePaths = {"kategori"})
    List<Buku> findByJudulContains(String judul);

    @EntityGraph(attributePaths = {"kategori"})
    List<Buku> findByPenerbitContains(String penerbit);

    @EntityGraph(attributePaths = {"kategori"})
    List<Buku> findByPengarangContains(String pengarang);

    @EntityGraph(attributePaths = {"kategori"})
    List<Buku> findByTahunTerbit(int tahunTerbit);

    @EntityGraph(attributePaths = {"kategori"})
    List<Buku> findByKategori_NamaContains(String kategori);

}
