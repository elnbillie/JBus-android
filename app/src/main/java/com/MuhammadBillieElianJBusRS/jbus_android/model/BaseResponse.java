package com.MuhammadBillieElianJBusRS.jbus_android.model;


/**
 * Kelas generik yang digunakan untuk menangani respons dasar dari API.
 * Kelas ini menyediakan struktur umum untuk respons yang dikirimkan oleh server,
 * termasuk status keberhasilan, pesan, dan data payload yang opsional.
 *
 * @param <T> Tipe data dari payload yang akan dikirim dalam respons.
 */
public class BaseResponse<T> {
    /**
     * Menandakan apakah operasi yang dilakukan sukses atau gagal.
     */
    public boolean success;
    /**
     * Pesan yang memberikan informasi lebih lanjut tentang respons,
     * bisa berupa pesan kesalahan atau konfirmasi.
     */
    public String message;
    /**
     * Payload dari respons. Tipe dari payload ini fleksibel dan ditentukan oleh
     * tipe generik {@code <T>}. Payload ini bisa berisi data yang relevan dengan
     * respons atau null jika tidak ada data yang dikirim.
     */
    public T payload;

}
