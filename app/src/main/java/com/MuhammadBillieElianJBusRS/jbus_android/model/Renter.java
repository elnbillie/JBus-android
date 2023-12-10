package com.MuhammadBillieElianJBusRS.jbus_android.model;

/**
 * Representasi penyewa dalam aplikasi.
 * Kelas ini menyimpan informasi tentang penyewa, termasuk nomor telepon, alamat, dan nama perusahaan.
 */
public class Renter extends Serializable {

    /**
     * Nomor telepon penyewa.
     */
    public String phoneNumber;

    /**
     * Alamat penyewa.
     */
    public String address;

    /**
     * Nama perusahaan penyewa.
     */
    public String companyName;

}
