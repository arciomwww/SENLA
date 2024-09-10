package org.example.database;

import org.example.entity.Feedback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

@Repository
public class FeedbackRepository {
    private final List<Feedback> feedbacks = new ArrayList<>();

    public FeedbackRepository() {
        //mock
        feedbacks.add(new Feedback.Builder()
                .setId(UUID.randomUUID())
                .setUserId(UUID.randomUUID())
                .setCarId(UUID.randomUUID())
                .setRating(5)
                .setComment("Great service!")
                .setCreatedAt(new Date())
                .build());
        feedbacks.add(new Feedback.Builder()
                .setId(UUID.randomUUID())
                .setUserId(UUID.randomUUID())
                .setCarId(UUID.randomUUID())
                .setRating(4)
                .setComment("Good service!")
                .setCreatedAt(new Date())
                .build());
    }

    public List<Feedback> findAll() {
        return feedbacks;
    }

    public Feedback findById(UUID id) {
        return feedbacks.stream().filter(feedback -> feedback.getId().equals(id)).findFirst().orElse(null);
    }

    public void save(Feedback feedback) {
        feedbacks.add(feedback);
    }

    public void delete(UUID id) {
        feedbacks.removeIf(feedback -> feedback.getId().equals(id));
    }
}