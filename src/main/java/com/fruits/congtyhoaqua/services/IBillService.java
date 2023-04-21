package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDTO;
import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.models.Bill;

import java.util.Set;

public interface IBillService {
    Bill createBill(Integer idUser,Integer idCustomer, Set<BillDetailDTO> billDetailDTOS);
    Set<Bill> getAllBillByIdUser(Integer idUser);
    Bill getBillById(Integer idBill);
    Set<Bill> filterByTime(String start, String end);
    Double thongKe(String start, String end);
    Set<Bill> getAllBill();
}
