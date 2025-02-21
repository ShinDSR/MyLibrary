package com.library.library.dto;

import jakarta.validation.constraints.NotEmpty;

public class KategoriDto {

    @NotEmpty(message = "Nama tidak boleh kosong")
    private String nama;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
