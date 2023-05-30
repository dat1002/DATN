package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.CategoryDTO;
import com.fruits.congtyhoaqua.dtos.KnowledgeDTO;
import com.fruits.congtyhoaqua.models.Knowledge;
import com.fruits.congtyhoaqua.services.IKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/knowledge")
@CrossOrigin(origins = "*")
public class KnowledgeController extends BaseController<Knowledge> {
    @Autowired
    private IKnowledgeService knowledgeService;

    @GetMapping("/get-all")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> selectAllKnowledge() {
        return this.resListSuccess(knowledgeService.selectAllKnowledge());
    }

    @PostMapping("/create")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> createKnowledge(@RequestBody KnowledgeDTO knowledgeDTO){
        return this.resSuccess(knowledgeService.createKnowledge(knowledgeDTO));
    }

    @PatchMapping("/edit/{idKnowledge}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editKnowledge(@PathVariable(name = "idKnowledge") Integer idKnowledge, @RequestBody KnowledgeDTO knowledgeDTO){
        return  this.resSuccess(knowledgeService.editKnowledge(idKnowledge, knowledgeDTO));
    }

    @PatchMapping("/avatar/{idKnowledge}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editAvatarKnowledge(@PathVariable(name = "idKnowledge") Integer idKnowledge, @RequestParam MultipartFile avatar){
        return  this.resSuccess(knowledgeService.editAvatarKnowledge(idKnowledge, avatar));
    }

    @DeleteMapping("/delete/{idKnowledge}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> deleteKnowledge(@PathVariable(name = "idKnowledge") Integer idKnowledge) {
        return this.resSuccess(knowledgeService.deleteKnowledge(idKnowledge));
    }


}