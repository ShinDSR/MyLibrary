package com.library.library.models.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "pengembalian")
public class Pengembalian implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "peminjaman_id", nullable = false)
    private Peminjaman peminjaman;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date tanggalDikembalikan;
    
    private Double denda;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
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
