package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	public static final String EMPTY = "";
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = saleRepository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleReportDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate max = maxDate.isBlank() || maxDate == null//
				? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault())  //
				: LocalDate.parse(maxDate); 
		
		LocalDate min = minDate.isBlank() || minDate == null//
				? max.minusYears(1L)  //
				: LocalDate.parse(minDate);
		
		Page<Sale> sale = saleRepository.getReport(min, max, name, pageable);
		
		return sale.map(s -> new SaleReportDTO(s));
	}
}
