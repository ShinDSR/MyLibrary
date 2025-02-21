package com.library.library.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;

public class PengembalianDto {

    @NotNull(message = "Peminjaman is required")
    private long peminjaman;

    @NotNull(message = "Tanggal Dikembalikan is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalDikembalikan;
    
    private Double denda;

    public long getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(long peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Date getTanggalDikembalikan() {
        return tanggalDikembalikan;
    }

    public void setTanggalDikembalikan(Date tanggalDikembalikan) {
        this.tanggalDikembalikan = tanggalDikembalikan;
    }

    public Double getDenda() {
        return denda;
    }

    public void setDenda(Double denda) {
        this.denda = denda;
    }
  
}
