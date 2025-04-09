package com.zynetic.bookstore.service;

import com.zynetic.bookstore.dto.BookDTO;
import com.zynetic.bookstore.entity.Book;
import com.zynetic.bookstore.exception.ResourceNotFoundException;
import com.zynetic.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private BookDTO convertToDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getCategory(),
                book.getRating()
        );
    }

    private Book convertToEntity(BookDTO dto) {
        return new Book(
                dto.getId(),
                dto.getTitle(),
                dto.getAuthor(),
                dto.getPrice(),
                dto.getCategory(),
                dto.getRating()
        );
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return convertToDTO(book);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book savedBook = bookRepository.save(convertToEntity(bookDTO));
        return convertToDTO(savedBook);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        existing.setTitle(bookDTO.getTitle());
        existing.setAuthor(bookDTO.getAuthor());
        existing.setPrice(bookDTO.getPrice());
        existing.setCategory(bookDTO.getCategory());
        existing.setRating(bookDTO.getRating());

        Book updated = bookRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> getBooksByCategory(String category) {
        return bookRepository.findByCategoryContainingIgnoreCase(category)
                .stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> searchBooks(String title, String author, String category, Double rating,
                                     Double minPrice, Double maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> result = bookRepository.findAll(pageable); // default

        if (title != null) {
            result = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else if (author != null) {
            result = bookRepository.findByAuthorContainingIgnoreCase(author, pageable);
        } else if (category != null) {
            result = bookRepository.findByCategoryContainingIgnoreCase(category, pageable);
        } else if (rating != null) {
            result = bookRepository.findByRatingGreaterThanEqual(rating, pageable);
        } else if (minPrice != null && maxPrice != null) {
            result = bookRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        }

        return result.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
