package org.example.service;

import org.example.dto.FeedbackDTO;
import org.example.database.FeedbackRepository;
import org.example.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    private FeedbackRepository feedbackRepository;

    @Autowired
    public void setFeedbackRepository(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<FeedbackDTO> getAllFeedback() {
        return feedbackRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public FeedbackDTO getFeedbackById(UUID id) {
        Feedback feedback = feedbackRepository.findById(id);
        return feedback != null ? convertToDTO(feedback) : null;
    }

    public void createFeedback(FeedbackDTO feedbackDTO) {
        Feedback feedback = convertToEntity(feedbackDTO);
        feedbackRepository.save(feedback);
    }

    public void updateFeedback(UUID id, FeedbackDTO feedbackDTO) {
        Feedback feedback = feedbackRepository.findById(id);
        if (feedback != null) {
            feedback.setComment(feedbackDTO.getComment());
            feedback.setRating(feedbackDTO.getRating());
            feedbackRepository.save(feedback);
        }
    }

    public void deleteFeedback(UUID id) {
        feedbackRepository.delete(id);
    }

    private FeedbackDTO convertToDTO(Feedback feedback) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setUserId(feedback.getUserId());
        feedbackDTO.setCarId(feedback.getCarId());
        feedbackDTO.setRating(feedback.getRating());
        feedbackDTO.setComment(feedback.getComment());
        feedbackDTO.setCreatedAt(feedback.getCreatedAt());
        return feedbackDTO;
    }

    private Feedback convertToEntity(FeedbackDTO feedbackDTO) {
        return new Feedback.Builder()
                .setId(feedbackDTO.getId())
                .setUserId(feedbackDTO.getUserId())
                .setCarId(feedbackDTO.getCarId())
                .setRating(feedbackDTO.getRating())
                .setComment(feedbackDTO.getComment())
                .setCreatedAt(feedbackDTO.getCreatedAt())
                .build();
    }
}

