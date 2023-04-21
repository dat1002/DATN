package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.BillDetail;

import java.util.Set;

public interface IBillDetailService {
    BillDetail createBillDetail(Integer idBill, BillDetailDTO billDetailDTO);
    Set<BillDetail> getAllBillDetailByIdBill(Integer idBill);
}
