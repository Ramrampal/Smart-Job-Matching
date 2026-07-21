package com.smartmatch.backend.service;

import com.smartmatch.backend.entity.Favorite;

import java.util.List;

public interface FavoriteService {

    Favorite addFavorite(Long userId, Long jobId);

    List<Favorite> getFavoritesByUser(Long userId);

    void removeFavorite(Long favoriteId);

}