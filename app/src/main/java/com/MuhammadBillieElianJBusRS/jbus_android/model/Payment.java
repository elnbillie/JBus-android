package com.MuhammadBillieElianJBusRS.jbus_android.model;

import java.sql.Timestamp;
import java.util.List;

/**
 * Representasi pembayaran dalam aplikasi.
 * Kelas ini merupakan ekstensi dari {@link Invoice} dan menyimpan informasi spesifik
 * terkait dengan pembayaran untuk sebuah perjalanan bus, termasuk ID bus, tanggal keberangkatan,
 * dan daftar kursi yang dipesan.
 */
public class Payment extends Invoice {

    /**
     * ID bus yang terkait dengan pembayaran ini.
     */
    public int busId;

    /**
     * Tanggal dan waktu keberangkatan bus.
     */
    public Timestamp departureDate;

    /**
     * Daftar kursi bus yang dipesan dalam pembayaran ini.
     */
    public List<String> busSeat;
}