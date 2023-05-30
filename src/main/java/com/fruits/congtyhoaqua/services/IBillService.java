package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.models.Bill;

import java.util.List;
import java.util.Set;

public interface IBillService {
    Bill createBill(Integer idUser, Set<BillDetailDTO> billDetailDTOS);
    List<Bill> getAllBillByIdUser(Integer idUser,Integer start, Integer size);
    Bill getBillById(Integer idBill);
    Bill editStatusBill(Integer idBill, Integer idOrderStatus);
    List<Bill> filterByTime(String start, String end);
    Double thongKe(String start, String end);
//    List<Bill> selectAllBill();
    List<Bill> selectAllBill(Integer start, Integer size);
    List<Bill> selectAllBillCancelled();
    Double dayRevenue();
    Double weeklyRevenue();
    Double monthlyRevenue();
    Double annualRevenue();

    Double dayProfit();
    Double weeklyProfit();
    Double monthlyProfit();
    Double annualProfit();
//    List<Float> annual();


}
