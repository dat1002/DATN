package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.BillDetail;

import java.util.List;
import java.util.Set;

public interface IBillDetailService {
    BillDetail createBillDetail(Integer idBill, BillDetailDTO billDetailDTO);
    List<BillDetail> findAllByIdBill(Integer idBill);
}
