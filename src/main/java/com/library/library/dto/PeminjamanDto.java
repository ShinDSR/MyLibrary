package com.library.library.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.library.library.models.entities.Buku;
import com.library.library.models.entities.User;

import jakarta.validation.constraints.NotNull;

public class PeminjamanDto {

    @NotNull(message = "Buku is required")
    private Buku buku;

    @NotNull(message = "User is required")
    private User user;

    @NotNull(message = "Tanggal Pinjam is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPinjam;

    @NotNull(message = "Tanggal Kembali is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalKembali;

    private boolean isKembali;

    public Buku getBuku() {
        return buku;
    }
    public void setBuku(Buku buku) {
        this.buku = buku;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }
    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }
    public Date getTanggalKembali() {
        return tanggalKembali;
    }
    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    public boolean isKembali() {
        return isKembali;
    }
    public void setKembali(boolean isKembali) {
        this.isKembali = isKembali;
    }

}
