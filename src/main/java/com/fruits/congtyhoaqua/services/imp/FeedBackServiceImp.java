package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Feedback;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.repositories.FeedBackRepository;
import com.fruits.congtyhoaqua.repositories.UserRepository;
import com.fruits.congtyhoaqua.services.IFeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class FeedBackServiceImp implements IFeedBackService {
    @Autowired
    private FeedBackRepository feedBackRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<Feedback> getAllFeedBack() {
        Set<Feedback> feedbacks = new HashSet<>(feedBackRepository.findAll());
        if(feedbacks.isEmpty()){
            throw new NotFoundException("No feedback");
        }
        return feedbacks;
    }

    @Override
    public Feedback createFeedBack(Integer idUser, String content) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
        Feedback feedback = new Feedback();
        feedback.setUser(user.get());
        feedback.setContent(content);
        return feedBackRepository.save(feedback);
    }


    @Override
    public Feedback getFeedBackById(Integer idFeedBack) {
        Optional<Feedback> feedback = feedBackRepository.findById(idFeedBack);
        if(feedback.isEmpty()){
            throw new NotFoundException("No feedback");
        }
        else
            return feedback.get();

    }

    @Override
    public Set<Feedback> getAllFeedBackByIdUser(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
        Set<Feedback> feedbacks = new HashSet<>(feedBackRepository.findAll());
        if(feedbacks.isEmpty()){
            throw new NotFoundException("No feedback");
        }
        return feedbacks;
    }
}
