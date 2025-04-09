package com.zynetic.bookstore.service;

import com.zynetic.bookstore.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);

   
    List<BookDTO> searchBooks(
        String title,
        String author,
        String category,
        Double rating,
        Double minPrice,
        Double maxPrice,
        int page,
        int size
    );

   
    List<BookDTO> getBooksByCategory(String category);
}
