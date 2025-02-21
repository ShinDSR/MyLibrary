package com.library.library.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BukuDto {

    @NotEmpty(message = "Judul tidak boleh kosong")
    private String judul;

    @NotEmpty(message = "Pengarang tidak boleh kosong")
    private String pengarang;

    @NotEmpty(message = "Penerbit tidak boleh kosong")
    private String penerbit;

    @NotNull(message = "Tahun terbit tidak boleh kosong")
    private Integer tahunTerbit;

    @NotNull(message = "Stok tidak boleh kosong")
    private Integer stok;

    @NotNull(message = "Kategori tidak boleh kosong")
    private Long kategori;

    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPengarang() {
        return pengarang;
    }
    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }
    public String getPenerbit() {
        return penerbit;
    }
    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
    public Integer getTahunTerbit() {
        return tahunTerbit;
    }
    public void setTahunTerbit(Integer tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
    public Integer getStok() {
        return stok;
    }
    public void setStok(Integer stok) {
        this.stok = stok;
    }
    public Long getKategori() {
        return kategori;
    }
    public void setKategori(Long kategori) {
        this.kategori = kategori;
    }
    
}
