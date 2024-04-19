package com.example.wishlistproject.service;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WishService {
    @Autowired
    private WishRepository wishRepository;

    public List<Wish> getAllWishes() {
        return wishRepository.findAll();
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    public void deleteWishById(Long id) {
        wishRepository.deleteWishById(id);
    }
}
