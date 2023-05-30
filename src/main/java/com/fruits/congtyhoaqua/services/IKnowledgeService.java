package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.KnowledgeDTO;
import com.fruits.congtyhoaqua.models.Feedback;
import com.fruits.congtyhoaqua.models.Knowledge;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
public interface IKnowledgeService {
    List<Knowledge> selectAllKnowledge();
    Knowledge createKnowledge(KnowledgeDTO knowledgeDTO);
    Knowledge deleteKnowledge(Integer idKnowledge);
    Knowledge editAvatarKnowledge(Integer idKnowledge, MultipartFile avatar);
    Knowledge editKnowledge(Integer idKnowledge,KnowledgeDTO knowledgeDTO);
}
