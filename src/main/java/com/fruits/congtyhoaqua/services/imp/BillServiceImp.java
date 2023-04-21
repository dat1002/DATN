package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.exceptions.BadRequestException;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.*;
import com.fruits.congtyhoaqua.models.id.BillDetailId;
import com.fruits.congtyhoaqua.repositories.*;
import com.fruits.congtyhoaqua.services.IBillService;
import com.fruits.congtyhoaqua.utils.Convert;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BillServiceImp implements IBillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private CustomerRepository customerRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private FruitRepository fruitRepository;

    @Override
    @Transactional
    public Bill createBill(Integer idUser, Integer idCustomer, Set<BillDetailDTO> billDetailDTOS) {
        // tạo bill
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            throw new NotFoundException("No User");
        }
// sửa phần tạo bill
        Bill bill = new Bill();
        bill.setUser(user.get());
        String randomCode = RandomStringUtils.randomAlphabetic(10);
        bill.setCode(randomCode);
        bill.setDateCreated(LocalDate.now());
        Bill bill1 = billRepository.save(bill);
        //tạo billDetail
        for (BillDetailDTO billDetailDTO :
                billDetailDTOS) {
            BillDetail billDetail = new BillDetail();
            Convert.fromBillDetailDTOToBillDetail(billDetailDTO, billDetail);
            billDetail.setBill(bill1);
            //thay đổi số lượng trong bảng hoa quả
            Optional<Fruit> fruit = fruitRepository.findById(billDetailDTO.getIdFruit());
            if (fruit.isEmpty()) {
                throw new NotFoundException("No fruit");
            }
            fruit.get().setAmount(fruit.get().getAmount() - billDetailDTO.getAmount());
            if (fruit.get().getAmount() < 0) {
                throw new BadRequestException("So luong Khong hop le");
            }
            billDetail.setFruit(fruitRepository.save(fruit.get()));
            //set lại ID cho bảng billDetail
            BillDetailId billDetailId = new BillDetailId(bill1.getId(), fruit.get().getId());

            billDetail.setBillDetailId(billDetailId);
            billDetailRepository.save(billDetail);
        }
        return bill1;
    }

    @Override
    public Set<Bill> getAllBillByIdUser(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            throw new NotFoundException("No User");
        }
        Set<Bill> bills = billRepository.findAllByUser(user.get());
        if (bills.isEmpty()) {
            throw new NotFoundException("No bill");
        }
        return bills;
    }

    @Override
    public Bill getBillById(Integer idBill) {
        Optional<Bill> bill = billRepository.findById(idBill);
        if (bill.isEmpty()) {
            throw new NotFoundException("No bill");
        }
        return bill.get();
    }

    @Override
    public Set<Bill> filterByTime(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
        if (bills.isEmpty()) {
            throw new NotFoundException("No bill");
        }
        return bills;
    }

    @Override
    public Double thongKe(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        Set<Bill> bills = new HashSet<>(billRepository.findAllByDateCreatedBetween(startDateConvert,endDateConvert));
        Double priceBillDetail = 0.0;
        Double priceBill = 0.0;
        for (Bill bill :
                bills) {
            Set<BillDetail> billDetails = bill.getBillDetails();
            for (BillDetail billDetail :
                    billDetails) {
                priceBillDetail = Double.valueOf(billDetail.getFruit().getPriceOut()* billDetail.getAmount());
                priceBill += priceBillDetail;
            }
        }
        return priceBill;
    }

    @Override
    public Set<Bill> getAllBill() {
        Set<Bill> bills = new HashSet<>(billRepository.findAll());
        if (bills.isEmpty()) {
            throw new NotFoundException("No bill");
        }
        return bills;
    }
}
