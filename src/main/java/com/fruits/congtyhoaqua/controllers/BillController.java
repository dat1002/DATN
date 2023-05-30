package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.services.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/bill-details")
@CrossOrigin(origins = "*")
public class BillController extends BaseController<Bill> {
    @Autowired
    private IBillService billService;

    @PostMapping("/{idUser}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> createBill(@PathVariable(name = "idUser") Integer idUser,

                                        @RequestBody Set<BillDetailDTO> billDetailDTOS){
        return this.resSuccess(billService.createBill(idUser,billDetailDTOS));
    }

    @GetMapping("/{idUser}/{start}/{size}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllBillByIdUser(@PathVariable(name = "idUser")Integer idUser,
                                                @PathVariable(name = "start")Integer start,
                                                @PathVariable(name = "size")Integer size
                                                ){
        return this.resListSuccess(billService.getAllBillByIdUser(idUser,start,size));
    }

    @GetMapping("/{idBill}/detail")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getBillById(@PathVariable(name = "idBill")Integer idBill){
        return this.resSuccess(billService.getBillById(idBill));
    }

    @GetMapping("/thongKe")
    public ResponseEntity<?> thongKe(@RequestParam String start,
                                     @RequestParam String end){
        return ResponseEntity.status(200).body(billService.filterByTime(start,end));
    }

    @GetMapping("/date")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> filterByTime(@RequestParam String start,
                                          @RequestParam String end){
        return this.resListSuccess(billService.filterByTime(start,end));
    }

    @PatchMapping("/edit/{idBill}/{idOrderStatus}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editStatusBill(@PathVariable(name = "idBill")  Integer idBill,
                                            @PathVariable(name = "idOrderStatus")  Integer idOrderStatus){
        return this.resSuccess(billService.editStatusBill(idBill,idOrderStatus));
    }

    @GetMapping("/{start}/{size}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllBill(@PathVariable(name = "start")  Integer start,
                                        @PathVariable(name = "size")  Integer size){
        return this.resListSuccess(billService.selectAllBill(start,size));
    }

    @GetMapping("/selectAllBillCancelled")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> selectAllBillCancelled(){
        return this.resListSuccess(billService.selectAllBillCancelled());
    }

    @GetMapping("/dayRevenue")
    public ResponseEntity<?> dayRevenue(){
        return ResponseEntity.status(200).body(billService.dayRevenue());
    }

    @GetMapping("/weeklyRevenue")
    public ResponseEntity<?> weeklyRevenue(){
        return ResponseEntity.status(200).body(billService.weeklyRevenue());
    }

    @GetMapping("/monthlyRevenue")
    public ResponseEntity<?> monthlyRevenue(){
        return ResponseEntity.status(200).body(billService.monthlyRevenue());
    }

    @GetMapping("/annualRevenue")
    public ResponseEntity<?> annualRevenue(){
        return ResponseEntity.status(200).body(billService.annualRevenue());
    }

    @GetMapping("/dayProfit")
    public ResponseEntity<?> dayProfit(){
        return ResponseEntity.status(200).body(billService.dayProfit());
    }

    @GetMapping("/weeklyProfit")
    public ResponseEntity<?> weeklyProfit(){
        return ResponseEntity.status(200).body(billService.weeklyProfit());
    }

    @GetMapping("/monthlyProfit")
    public ResponseEntity<?> monthlyProfit(){
        return ResponseEntity.status(200).body(billService.monthlyProfit());
    }

    @GetMapping("/annualProfit")
    public ResponseEntity<?> annualProfit(){
        return ResponseEntity.status(200).body(billService.annualProfit());
    }

//    @GetMapping("/annual")
//    public ResponseEntity<?> annual(){
//        return this.resListSuccess(billService.annual());
//    }
}
