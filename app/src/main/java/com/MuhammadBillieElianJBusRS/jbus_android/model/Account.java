package com.MuhammadBillieElianJBusRS.jbus_android.model;

public class Account extends Serializable {
    /**
     * Nama pengguna.
     */
    public String name;

    /**
     * Alamat email pengguna.
     */
    public String email;

    /**
     * Kata sandi untuk akun pengguna.
     */
    public String password;

    /**
     * Saldo akun pengguna.
     */
    public double balance;
    /**
     * Perusahaan penyewa yang terkait dengan pengguna
     */
    public Renter company;
}
