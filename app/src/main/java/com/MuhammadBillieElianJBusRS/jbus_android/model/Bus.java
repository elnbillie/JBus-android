package com.MuhammadBillieElianJBusRS.jbus_android.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representasi bus dalam aplikasi.
 * Kelas ini menyimpan informasi terkait bus, termasuk ID akun, nama,
 * fasilitas, harga, kapasitas, tipe bus, stasiun keberangkatan dan kedatangan,
 * serta jadwal bus.
 */
public class Bus extends Serializable {

    public int accountId;
    /**
     * ID akun yang terkait dengan bus.
     */
    /**
     * Nama bus.
     */
    public String name;

    /**
     * Daftar fasilitas yang tersedia di bus.
     */
    public List<Facility> facilities;

    /**
     * Harga tiket untuk bus.
     */
    public Price price;

    /**
     * Kapasitas maksimum penumpang yang dapat ditampung oleh bus.
     */
    public int capacity;

    /**
     * Tipe bus.
     */
    public BusType busType;

    /**
     * Stasiun keberangkatan bus.
     */
    public Station departure;

    /**
     * Stasiun kedatangan bus.
     */
    public Station arrival;

    /**
     * Jadwal keberangkatan bus.
     */
    public List<Schedule> schedules;

    /**
     * Menghasilkan daftar sampel bus.
     *
     * @param size Jumlah bus yang diinginkan dalam daftar.
     * @return Daftar bus yang dibuat secara acak.
     */
    public static List<Bus> sampleBusList(int size) {
        List<Bus> busList = new ArrayList<>();

        Random random = new Random();
        City[] cities = City.values();

        for (int i = 1; i <= size; i++) {
            Bus bus = new Bus();
            bus.name = "Bus " + i;

            City departureCity = cities[random.nextInt(cities.length)];
            Station departureStation = new Station();
            departureStation.city = departureCity;
            bus.departure = departureStation;

            City arrivalCity;
            do {
                arrivalCity = cities[random.nextInt(cities.length)];
            } while (arrivalCity == departureCity);
            Station arrivalStation = new Station();
            arrivalStation.city = arrivalCity;
            bus.arrival = arrivalStation;
            busList.add(bus);
        }

        return busList;
    }

    /**
     * Mengembalikan representasi String dari bus.
     *
     * @return Nama bus.
     */

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
