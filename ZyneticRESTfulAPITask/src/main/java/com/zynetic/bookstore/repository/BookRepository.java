package com.zynetic.bookstore.repository;

import com.zynetic.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Book> findByAuthorContainingIgnoreCase(String author, Pageable pageable);
    Page<Book> findByCategoryContainingIgnoreCase(String category, Pageable pageable);
    Page<Book> findByRatingGreaterThanEqual(double rating, Pageable pageable);
    Page<Book> findByPriceBetween(Double minPrice, Double maxPrice, Pageable pageable);

    
    List<Book> findByCategoryContainingIgnoreCase(String category);
}
