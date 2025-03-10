package com.PaginationAndSorting.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.PaginationAndSorting.Entity.Product;
import com.PaginationAndSorting.Service.ProductService;

@RestController
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@PostMapping("save/{id}")
	public ResponseEntity<String> saveProduct(@PathVariable int id) {
		try {
			productService.save(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("failed to save product : " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("saved product with id : " + id, HttpStatus.OK);
	}

	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "3") int pageSize) {
		// Ensure pageNo and pageSize are valid
		if (pageNo < 0) {
			return ResponseEntity.badRequest().body(null);
		}
		if (pageSize <= 0) {
			return ResponseEntity.badRequest().body(null);
		}
		Page<Product> perPageProducts = productService.getAllProducts(pageNo, pageSize);
		logger.info("Total data count: {}", perPageProducts.getTotalElements());
		logger.info("Total Page count: {}", perPageProducts.getTotalPages());
		logger.info("Per Page size: {}", perPageProducts.getSize());
		logger.info("Current page Number: {}", perPageProducts.getNumber());
		logger.info("Page Data as List: {}", perPageProducts.getContent());
		logger.info("Is next page available? {}", perPageProducts.hasNext());
		logger.info("Is previous page available? {}", perPageProducts.hasPrevious());
		return ResponseEntity.ok(perPageProducts);
	}

	// sort by sort method
	@GetMapping("sortbyprice")
	public ResponseEntity<List<Product>> getsortedProductByPrice() {
		List<Product> listsorted = productService.sortedProducts();
		return ResponseEntity.ok(listsorted);
	}

	// paginationa and sorting at a time
	@GetMapping("productedsortedandpagination")
	public ResponseEntity<Page<Product>> getPaginatedAndSortedProducts(@RequestParam(defaultValue = "0") int pageNumber,
			@RequestParam(defaultValue = "3") int pageSize, @RequestParam(defaultValue = "price") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		// Validate Page Number and Size
		if (pageNumber < 0) {
			return ResponseEntity.badRequest().body(null);
		}
		if (pageSize <= 0) {
			return ResponseEntity.badRequest().body(null);
		}
		Page<Product> productPage = productService.pageAndSortProducts(pageNumber, pageSize, sortBy, sortDirection);
		logger.info("Returning {} products for page {} with sorting by {} ({})", productPage.getNumberOfElements(),
				pageNumber, sortBy, sortDirection);
		return ResponseEntity.ok(productPage);
	}
}
