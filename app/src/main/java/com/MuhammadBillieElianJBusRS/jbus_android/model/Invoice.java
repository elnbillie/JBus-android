package com.MuhammadBillieElianJBusRS.jbus_android.model;

import java.sql.Timestamp;

/**
 * Representasi invoice dalam aplikasi.
 * Kelas ini menyimpan informasi terkait invoice termasuk waktu pembuatan, ID pembeli dan penyewa,
 * rating bus, dan status pembayaran.
 */
public class Invoice extends Serializable {

    /**
     * Waktu pembuatan invoice.
     */
    public Timestamp time;

    /**
     * ID pembeli yang terkait dengan invoice ini.
     */
    public int buyerId;

    /**
     * ID penyewa yang terkait dengan invoice ini.
     */
    public int renterId;

    /**
     * Rating yang diberikan untuk bus.
     */
    public BusRating rating;

    /**
     * Status pembayaran invoice.
     */
    public PaymentStatus status;

    /**
     * Enumerasi untuk rating bus.
     */
    public enum BusRating {
        NONE,    // Tidak ada rating
        NEUTRAL, // Rating netral
        GOOD,    // Rating baik
        BAD;     // Rating buruk
    }

    /**
     * Enumerasi untuk status pembayaran.
     */
    public enum PaymentStatus {
        FAILED,  // Pembayaran gagal
        WAITING, // Pembayaran menunggu
        SUCCESS; // Pembayaran berhasil
    }
}
