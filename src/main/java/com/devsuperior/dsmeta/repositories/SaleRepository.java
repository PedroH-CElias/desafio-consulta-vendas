package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query(
		    value = "SELECT s FROM Sale s JOIN s.seller seller " +
		            "WHERE s.date BETWEEN :minDate AND :maxDate " +
		            "AND UPPER(seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",

		    countQuery = "SELECT COUNT(s) FROM Sale s JOIN s.seller seller " +
		                 "WHERE s.date BETWEEN :minDate AND :maxDate " +
		                 "AND UPPER(seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
		)
	
	Page<Sale> getReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
