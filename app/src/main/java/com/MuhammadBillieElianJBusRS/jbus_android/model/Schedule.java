package com.MuhammadBillieElianJBusRS.jbus_android.model;

import androidx.annotation.NonNull;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Kelas `Schedule` mewakili jadwal keberangkatan bus dan ketersediaan kursi pada jadwal tersebut.
 *
 * @author Muhammad Billie Elian
 */
public class Schedule {
    /**
     * Waktu keberangkatan bus.
     */
    public Timestamp departureSchedule;
    /**
     * Ketersediaan kursi pada jadwal ini.
     * Key pada map adalah nomor kursi, dan value berupa true jika kursi tersedia dan false jika tidak tersedia.
     */
    public Map<String, Boolean> seatAvailability;
    /**
     * Menampilkan informasi jadwal keberangkatan dan ketersediaan kursi.
     *
     * @return String yang berisi informasi jadwal keberangkatan dan ketersediaan kursi.
     */

    @NonNull
    @Override
    public String toString() {
        int countOccupied = 0;
        for (boolean val : seatAvailability.values()) {
            if (!val) countOccupied++;
        }
        int totalSeat = seatAvailability.size();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy HH:mm:ss");
        return dateFormat.format(this.departureSchedule.getTime()) + "\t\t" +"[ "+countOccupied + "/" + totalSeat+" ]";
    }
}
