package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.models.Feedback;

import java.util.Set;

public interface IFeedBackService {
    Set<Feedback> getAllFeedBack();
    Feedback createFeedBack(Integer idUser,String content);
    Feedback getFeedBackById(Integer idFeedBack);
    Set<Feedback> getAllFeedBackByIdUser(Integer idUser);
}
