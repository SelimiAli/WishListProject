package com.example.wishlistproject.repository;
import com.example.wishlistproject.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WishRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wish> findAll() {
        return jdbcTemplate.query("SELECT * FROM wish", this::mapRowToWish);
    }

    public void save(Wish wish) {
        jdbcTemplate.update("INSERT INTO wish (description, created_at) VALUES (?, ?)",
                wish.getDescription(), wish.getCreatedAt());
    }

    public void deleteWishById(Long id) {
        jdbcTemplate.update("DELETE FROM wish WHERE id = ?", id);
    }

    private Wish mapRowToWish(ResultSet rs, int rowNum) throws SQLException {
        Wish wish = new Wish();
        wish.setId(rs.getLong("id"));
        wish.setDescription(rs.getString("description"));
        wish.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        return wish;
    }
}