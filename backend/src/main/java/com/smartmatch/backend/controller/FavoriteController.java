package com.smartmatch.backend.controller;

import com.smartmatch.backend.entity.Favorite;
import com.smartmatch.backend.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public Favorite addFavorite(@RequestParam Long userId,
                                @RequestParam Long jobId) {
        return favoriteService.addFavorite(userId, jobId);
    }

    @GetMapping("/user/{userId}")
    public List<Favorite> getFavoritesByUser(@PathVariable Long userId) {
        return favoriteService.getFavoritesByUser(userId);
    }

    @DeleteMapping("/{favoriteId}")
    public String removeFavorite(@PathVariable Long favoriteId) {
        favoriteService.removeFavorite(favoriteId);
        return "Favorite removed successfully";
    }
}