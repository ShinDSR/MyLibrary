package com.library.library.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.library.library.models.entities.Peminjaman;

import jakarta.validation.constraints.NotNull;

public class PengembalianDto {

    @NotNull(message = "Peminjaman is required")
    private Peminjaman peminjaman;

    @NotNull(message = "Tanggal Dikembalikan is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalDikembalikan;
    
    private Double denda;

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
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
