package com.bookstore.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

    private String author;
    private String title;
    private String category;
    private double price;
    private int totalCount;
    private int sold;

}
