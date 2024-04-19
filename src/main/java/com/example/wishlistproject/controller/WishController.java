package com.example.wishlistproject.controller;
import com.example.wishlistproject.model.Wish;
import com.example.wishlistproject.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WishController {
    private final WishService wishService;

    @Autowired
    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Wish> wishes;
        try {
            wishes = wishService.getAllWishes();
        } catch (Exception e) {
            // Log fejlmeddelelse
            e.printStackTrace();
            // Tilføj en fejlbesked til modellen
            model.addAttribute("error", "Fejl ved indlæsning af ønsker. Prøv igen senere.");
            wishes = List.of(); // Tom liste som backup
        }
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishes", wishes);
        return "index";
    }

    @GetMapping("/create-wishlist") // Den nye metode for at vise "Create Wishlist" siden
    public String showCreateWishlistPage() {
        return "index"; // Returner navnet på HTML-siden for "Create Wishlist"
    }

    @PostMapping("/save")
    public String saveWish(@ModelAttribute("wish") Wish wish) {
        try {
            if (wish.getDescription().isEmpty()) {
                // Håndter tom beskrivelse
                return "redirect:/create-wishlist"; // Ændret omdirigering til "create-wishlist"
            }
            // Initialiser createdAt-feltet med den aktuelle dato og tid
            wish.setCreatedAt(LocalDateTime.now());
            wishService.saveWish(wish);
        } catch (Exception e) {
            // Håndter fejl
            e.printStackTrace();
        }
        return "redirect:/"; // Omdirigerer tilbage til rodstien, når oprettelsen er fuldført
    }


    @PostMapping("/delete/{id}")
    public String deleteWish(@PathVariable("id") Long id) {
        try {
            wishService.deleteWishById(id);
        } catch (Exception e) {
            // Håndter fejl
            e.printStackTrace();
        }
        return "redirect:/";
    }
}