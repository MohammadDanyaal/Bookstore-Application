package com.zynetic.bookstore.dto;

import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private String category;
    private double rating;
}