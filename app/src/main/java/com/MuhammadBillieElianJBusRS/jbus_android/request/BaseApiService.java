package com.MuhammadBillieElianJBusRS.jbus_android.request;

import com.MuhammadBillieElianJBusRS.jbus_android.model.Account;
import com.MuhammadBillieElianJBusRS.jbus_android.model.BaseResponse;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Bus;
import com.MuhammadBillieElianJBusRS.jbus_android.model.BusType;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Facility;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Payment;
import com.MuhammadBillieElianJBusRS.jbus_android.model.Station;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApiService {
    @POST("account/login")
    Call<BaseResponse<Account>> login (
            @Query("email") String email,
            @Query("password") String password);

    @POST("account/register")
    Call<BaseResponse<Account>> register (
            @Query("name") String name,
            @Query("email") String email,
            @Query("password") String password);

    @GET("account/{id}")
    Call<Account> getAccountbyId(@Path("id") int id);

    @POST("account/{id}/registerRenter")
    Call<BaseResponse<Account>> registerRenter(
            @Path("id") int id,
            @Query("companyName") String companyName,
            @Query("address") String address,
            @Query("phoneNumber") String phoneNumber);

    @GET("bus/total")
    Call<Integer> numberOfBuses();

    @GET("bus/page")
    Call<List<Bus>> getBus(@Query("page") int page, @Query("size") int pageSize);

    @POST("bus/create")
    Call<BaseResponse<Bus>> addBus(
            @Query("accountId") int accountId,
            @Query("name") String name,
            @Query("capacity") int capacity,
            @Query("facilities") List<Facility> facilities,
            @Query("busType") BusType busType,
            @Query("price") int price,
            @Query("stationDepartureId") int stationDepartureId,
            @Query("stationArrivalId") int stationArrivalId
    );

    @GET("bus/getMyBus")
    Call<List<Bus>> getMyBus(@Query("accountId") int accountId);

    @GET("bus/{id}")
    Call<Bus> getBusbyId(@Path("id") int busId);

    @POST("bus/addSchedule")
    Call<BaseResponse<Bus>> addSchedule(@Query("busId") int busId,
                                        @Query("time") String time);

    @POST("account/{id}/topUp")
    Call<BaseResponse<Double>> topUp (@Path("id") int id, @Query("amount") double amount);

    @GET("station/getAll")
    Call<List<Station>> getAllStation();

    @POST("payment/makeBooking")
    Call<BaseResponse<Payment>> makeBooking(
            @Query("buyerId") int buyerId,
            @Query("renterId") int renterId,
            @Query("busId") int busId,
            @Query("busSeats") List<String> busSeats,
            @Query("departureDate") String departureDate
    );

    @GET("bus/seats")
    Call<Map<String,Boolean>> getSeats(@Query("busId") int busId,
                                       @Query("date") String date);

    @POST("payment/{id}/cancel")
    Call<BaseResponse<Payment>> cancel(
            @Path("id") int id
    );
    @POST("payment/{id}/accept")
    Call<BaseResponse<Payment>> accept(
            @Path("id") int id
    );

    @GET("payment/getAccountPayment")
    Call<List<Payment>> getBuyerPayment(
            @Query("accountId") int accountId
    );

    @GET("payment/getRenterPayment")
    Call<List<Payment>> getRenterPayment(
            @Query("renterId") int renterId
    );
}
