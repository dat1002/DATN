package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.BillDetail;
import com.fruits.congtyhoaqua.repositories.BillDetailRepository;
import com.fruits.congtyhoaqua.repositories.BillRepository;
import com.fruits.congtyhoaqua.services.IBillDetailService;
import com.fruits.congtyhoaqua.services.IBillService;
import com.fruits.congtyhoaqua.services.IFruitService;
import com.fruits.congtyhoaqua.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BillDetailServiceImp implements IBillDetailService {

    @Autowired
    private IFruitService fruitService;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private BillRepository billRepository;

    @Override
    public BillDetail createBillDetail(Integer idBill, BillDetailDTO billDetailDTO) {
        Optional<Bill> bill = billRepository.findById(idBill);
        if (bill.isEmpty()){
            throw new NotFoundException("No bill");
        }
        BillDetail billDetail = new BillDetail();
        Convert.fromBillDetailDTOToBillDetail(billDetailDTO,billDetail);
        billDetail.setBill(bill.get());
        return billDetail;
    }

    @Override
    public List<BillDetail> findAllByIdBill(Integer idBill) {
        Optional<Bill> bill = billRepository.findById(idBill);
        if (bill.isEmpty()){
            throw new NotFoundException("No bill");
        }
        List<BillDetail> billDetails = new ArrayList<>(billDetailRepository.findAllByIdBill(idBill));
        if (billDetails.isEmpty()){
            throw new NotFoundException("No bill details");
        }
        return billDetails;
    }
}
