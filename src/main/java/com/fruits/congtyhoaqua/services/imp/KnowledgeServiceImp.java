package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.KnowledgeDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Category;
import com.fruits.congtyhoaqua.models.Knowledge;
import com.fruits.congtyhoaqua.repositories.KnowledgeRepository;
import com.fruits.congtyhoaqua.services.IKnowledgeService;
import com.fruits.congtyhoaqua.utils.Convert;
import com.fruits.congtyhoaqua.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class KnowledgeServiceImp implements IKnowledgeService {
    @Autowired private KnowledgeRepository knowledgeRepository;

    @Autowired
    private UploadFile uploadFile;

    @Override
    public List<Knowledge> selectAllKnowledge() {
        List<Knowledge> knowledges = new ArrayList<>(knowledgeRepository.selectAllKnowledge());
        if(knowledges.isEmpty()){
            throw new NotFoundException("No knowledge");
        }
        return knowledges;
    }

    @Override
    public Knowledge createKnowledge(KnowledgeDTO knowledgeDTO) {
        Knowledge knowledge = new Knowledge();
        Convert.fromKnowledgeDTOToKnowledge(knowledgeDTO, knowledge);
        return knowledgeRepository.save(knowledge);
    }

    @Override
    public Knowledge deleteKnowledge(Integer idKnowledge) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(idKnowledge);
        if(knowledge.isEmpty()){
            throw new NotFoundException("No knowledge");
        }
        knowledge.get().setStatus(false);
        return knowledgeRepository.save(knowledge.get());
    }

    @Override
    public Knowledge editAvatarKnowledge(Integer idKnowledge, MultipartFile avatar) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(idKnowledge);
        if (knowledge.isEmpty()){
            throw new NotFoundException("No knowledge");
        }
        knowledge.get().setAvatar(uploadFile.getUrlFromFile(avatar));
        return knowledgeRepository.save(knowledge.get());
    }

    @Override
    public Knowledge editKnowledge(Integer idKnowledge,KnowledgeDTO knowledgeDTO) {
        Optional<Knowledge> knowledge = knowledgeRepository.findById(idKnowledge);
        if(knowledge.isEmpty()){
            throw new NotFoundException("No knowledge");
        }
        Convert.fromKnowledgeDTOToKnowledge(knowledgeDTO, knowledge.get());
        return knowledgeRepository.save(knowledge.get());
    }
}
