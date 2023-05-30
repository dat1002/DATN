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
import java.util.*;

@Service
public class BillServiceImp implements IBillService {
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FruitRepository fruitRepository;


//    @Autowired
//    private CustomerRepository customerRepository;

    @Autowired
    private BillDetailRepository billDetailRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Override
    @Transactional
    public Bill createBill(Integer idUser, Set<BillDetailDTO> billDetailDTOS) {
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
        bill.setPaymentMethod("Thanh toán khi nhận hàng.");
        bill.setPaymentStatus("Chưa thanh toán.");
        Optional<OrderStatus> orderStatus  = orderStatusRepository.findById(1);
        bill.setOrderStatus(orderStatus.get());
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
    public List<Bill> getAllBillByIdUser(Integer idUser,Integer start, Integer size) {
        Optional<User> user = userRepository.findById(idUser);
        if (user.isEmpty()) {
            throw new NotFoundException("No User");
        }
        List<Bill> bills = billRepository.findAllByUser(idUser,start,size);
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
    public List<Bill> filterByTime(String start, String end) {
        LocalDate startDateConvert = LocalDate.parse(start);
        LocalDate endDateConvert = LocalDate.parse(end);
        List<Bill> bills = new ArrayList<>(billRepository.findAllByDateCreatedBetween(startDateConvert, endDateConvert));
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
    public List<Bill> selectAllBill(Integer start, Integer size) {
        List<Bill> bills = new ArrayList<>(billRepository.selectAllBill(start,size));
        if (bills.isEmpty()) {
            throw new NotFoundException("No bill");
        }
        return bills;
    }

    @Override
    public List<Bill> selectAllBillCancelled() {
        List<Bill> bills = new ArrayList<>(billRepository.selectAllBillCancelled());
        if (bills.isEmpty()) {
            throw new NotFoundException("No bill");
        }
        return bills;
    }

    @Override
    public Double dayRevenue() {
        Double rev = billRepository.dayRevenue();
        return rev;
    }

    @Override
    public Double weeklyRevenue() {
        Double rev = billRepository.weeklyRevenue();
        return rev;
    }

    @Override
    public Double monthlyRevenue() {
        Double rev = billRepository.monthlyRevenue();
        return rev;
    }

    @Override
    public Double annualRevenue() {
        Double rev = billRepository.annualRevenue();
        return rev;
    }

    @Override
    public Double dayProfit() {
        Double rev = billRepository.dayProfit();
        return rev;
    }

    @Override
    public Double weeklyProfit() {
        Double rev = billRepository.weeklyProfit();
        return rev;
    }

    @Override
    public Double monthlyProfit() {
        Double rev = billRepository.monthlyProfit();
        return rev;
    }

    @Override
    public Double annualProfit() {
        Double rev = billRepository.annualProfit();
        return rev;
    }

//    @Override
//    public List<Float> annual() {
//        List<Float> revs = new ArrayList<>(billRepository.annual());
//
//        return revs;
//    }


    @Override
    public Bill editStatusBill(Integer idBill, Integer idOrderStatus) {
        Optional<Bill> bill = billRepository.findById(idBill);
        if (bill.isEmpty()) {
            throw new NotFoundException("No bill");
        }

        if (idOrderStatus == 4) {
            bill.get().setStatus(Boolean.FALSE);
            List<BillDetail> billDetails = new ArrayList<>(billDetailRepository.findAllByIdBill(idBill));
            for (BillDetail billDetail: billDetails
                 ) {
               Optional<Fruit> fruit =  fruitRepository.findById(billDetail.getFruit().getId());
               fruit.get().setAmount(fruit.get().getAmount()+billDetail.getAmount());
               fruitRepository.save(fruit.get());
            }
        }
        Optional<OrderStatus> orderStatus  = orderStatusRepository.findById(idOrderStatus);
        bill.get().setOrderStatus(orderStatus.get());
        billRepository.save(bill.get());
        return bill.get();
    }
}
