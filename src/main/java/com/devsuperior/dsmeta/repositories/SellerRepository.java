package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	
	@Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO(" +
		       "s.seller.name, SUM(s.amount)) " +
		       "FROM Sale s " +
		       "WHERE s.date BETWEEN :minDate AND :maxDate " +
		       "GROUP BY s.seller.name")
	Page<SaleSummaryDTO> getSummary(LocalDate minDate, LocalDate maxDate, Pageable pageable);

}
