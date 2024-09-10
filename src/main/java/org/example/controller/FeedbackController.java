package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.FeedbackDTO;
import org.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Date;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;
    private ObjectMapper objectMapper;

    @Autowired
    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/initialize")
    public void initializeFeedback() {
        FeedbackDTO feedback1 = new FeedbackDTO();
        feedback1.setId(UUID.randomUUID());
        feedback1.setUserId(UUID.randomUUID());
        feedback1.setCarId(UUID.randomUUID());
        feedback1.setRating(5);
        feedback1.setComment("Great car!");
        feedback1.setCreatedAt(new Date());

        FeedbackDTO feedback2 = new FeedbackDTO();
        feedback2.setId(UUID.randomUUID());
        feedback2.setUserId(UUID.randomUUID());
        feedback2.setCarId(UUID.randomUUID());
        feedback2.setRating(4);
        feedback2.setComment("Good service.");
        feedback2.setCreatedAt(new Date());

        feedbackService.createFeedback(feedback1);
        feedbackService.createFeedback(feedback2);
    }

    @GetMapping
    public String getAllFeedback() throws Exception {
        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedback();
        return objectMapper.writeValueAsString(feedbackList);
    }

    @GetMapping("/{id}")
    public String getFeedbackById(@PathVariable UUID id) throws Exception {
        FeedbackDTO feedback = feedbackService.getFeedbackById(id);
        return objectMapper.writeValueAsString(feedback);
    }

    @PostMapping
    public void createFeedback(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.createFeedback(feedbackDTO);
    }

    @PutMapping("/{id}")
    public void updateFeedback(@PathVariable UUID id, @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.updateFeedback(id, feedbackDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable UUID id) {
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/test")
    public void testCRUDOperations() throws Exception {
        initializeFeedback();

        List<FeedbackDTO> feedbackList = feedbackService.getAllFeedback();
        FeedbackDTO feedbackToRead = feedbackList.get(0);
        System.out.println("Read Feedback: " + objectMapper.writeValueAsString(feedbackService.getFeedbackById(feedbackToRead.getId())));

        FeedbackDTO feedbackToDelete = feedbackList.get(1);
        feedbackService.deleteFeedback(feedbackToDelete.getId());

        feedbackToRead.setComment("Updated comment");
        feedbackService.updateFeedback(feedbackToRead.getId(), feedbackToRead);

        System.out.println("Updated Feedback: " + objectMapper.writeValueAsString(feedbackService.getFeedbackById(feedbackToRead.getId())));
    }
}