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

    @PostMapping("/{idUser}/{idCustomer}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> createBill(@PathVariable(name = "idUser") Integer idUser,
                                        @PathVariable(name = "idCustomer")Integer idCustomer,
                                        @RequestBody Set<BillDetailDTO> billDetailDTOS){
        return this.resSuccess(billService.createBill(idUser, idCustomer,billDetailDTOS));
    }

    @GetMapping("/{idUser}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllBillByIdUser(@PathVariable(name = "idUser")Integer idUser){
        return this.resSetSuccess(billService.getAllBillByIdUser(idUser));
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
        return this.resSetSuccess(billService.filterByTime(start,end));
    }

    @GetMapping
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllBill(){
        return this.resSetSuccess(billService.getAllBill());
    }
}
