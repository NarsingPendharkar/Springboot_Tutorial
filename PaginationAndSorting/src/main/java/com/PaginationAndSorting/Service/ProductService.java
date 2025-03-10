package com.PaginationAndSorting.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.PaginationAndSorting.DAO.ProductRepository;
import com.PaginationAndSorting.Entity.Product;

@Service
public class ProductService {
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private ProductRepository productRepository;

	public ResponseEntity<String> save(int id) {
		String url = "https://dummyjson.com/products/" + id;
		ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
		if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
			Product fetched = response.getBody();
			Product sa = new Product();
			sa.setPrice(fetched.getPrice());
			sa.setTitle(fetched.getTitle());
			productRepository.save(sa);
			return new ResponseEntity<String>("saved product -" + sa.getTitle(), HttpStatus.OK);
		} else {
			System.out.println("failed" + response.getStatusCode());
			return new ResponseEntity<String>("failed to save product", HttpStatus.BAD_REQUEST);
		}
	}

	// pagination
	public Page<Product> getAllProducts(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Product> pageList = productRepository.findAll(pageable);
		return pageList;
	}

	// sort product using sort method
	public List<Product> sortedProducts() {
		return productRepository.findAll(Sort.by("price").descending());
	}

	public Page<Product> pageAndSortProducts(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Product> sorted = productRepository.findAll(pageable);
		return sorted;
	}
}
