package com.smartmatch.backend.service.impl;

import com.smartmatch.backend.entity.Favorite;
import com.smartmatch.backend.entity.Job;
import com.smartmatch.backend.entity.User;
import com.smartmatch.backend.repository.FavoriteRepository;
import com.smartmatch.backend.repository.JobRepository;
import com.smartmatch.backend.repository.UserRepository;
import com.smartmatch.backend.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Favorite addFavorite(Long userId, Long jobId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setJob(job);

        return favoriteRepository.save(favorite);
    }

    @Override
    public List<Favorite> getFavoritesByUser(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Override
    public void removeFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }
}