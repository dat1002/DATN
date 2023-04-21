package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.models.Feedback;
import com.fruits.congtyhoaqua.services.IFeedBackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedBackController extends BaseController<Feedback> {
    @Autowired
    private IFeedBackService feedBackService;

    @GetMapping
    public ResponseEntity<?> getAllFeedBack() {
        return this.resSetSuccess(feedBackService.getAllFeedBack());
    }

    @GetMapping("/{idFeedBack}")
    public ResponseEntity<?> getFeedBackById(@PathVariable(name = "idFeedBack")Integer idFeedBack)
    {
        return this.resSuccess(feedBackService.getFeedBackById(idFeedBack));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<?> getAllFeedBackByIdUser(@PathVariable(name = "idUser")Integer idUser){
        return this.resSetSuccess(feedBackService.getAllFeedBackByIdUser(idUser));
    }

    @PostMapping("/create/{idUser}")
    public ResponseEntity<?> createFeedBack(@PathVariable(name = "idUser")Integer idUser,
                                            @RequestParam String content){
        return this.resSuccess(feedBackService.createFeedBack(idUser, content));
    }



}
