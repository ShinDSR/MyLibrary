package com.library.library.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public class PeminjamanDto {

    @NotNull(message = "Buku is required")
    private long buku;

    @NotNull(message = "User is required")
    private long user;

    @NotNull(message = "Tanggal Pinjam is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPinjam;

    @NotNull(message = "Tanggal Kembali is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalKembali;

    @NotNull(message = "Kembali is required")
    private boolean isKembali;

    public long getBuku() {
        return buku;
    }
    public void setBuku(long buku) {
        this.buku = buku;
    }
    public long getUser() {
        return user;
    }
    public void setUser(long user) {
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
