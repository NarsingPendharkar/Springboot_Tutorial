package com.PaginationAndSorting.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.PaginationAndSorting.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	void save(ResponseEntity<Product> product);

	// by using sort method
	// by using paginationa and sorting
	Page<Product> findByPrice(double price, Pageable pageable);

	// Custom Sorting with @Query Annotation
	@Query(value = "select all from products order by price desc ", nativeQuery = true)
	List<Product> customSortedbyPrice();
}
