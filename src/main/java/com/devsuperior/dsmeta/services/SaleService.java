package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSumaryDTO;
import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private LocalDate maxDate;
	private LocalDate minDate;

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSumaryDTO> getSalesSumary(String minDate, String maxDate) {
		 this.maxDate = (maxDate == null || maxDate.isBlank()) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :  LocalDate.parse(maxDate) ;
		 this.minDate = (minDate == null || minDate.isBlank()) ? this.maxDate.minusYears(1L) : LocalDate.parse(minDate) ;

		List<SaleSumaryProjection> result = repository.salesSumary(this.minDate, this.maxDate);
		List<SaleSumaryDTO> list = result.stream().map(x -> new SaleSumaryDTO(x)).collect(Collectors.toList());
		return list;
	}

	public Page<SaleReportDTO> getSalesReport( String minDate, String maxDate, String name, Pageable pageable) {
		this.maxDate = (maxDate == null || maxDate.isBlank()) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) :  LocalDate.parse(maxDate) ;
		this.minDate = (minDate == null || minDate.isBlank()) ? this.maxDate.minusYears(1L) : LocalDate.parse(minDate) ;

		Page<SaleReportDTO> result = repository.salesReport(this.minDate, this.maxDate, name, pageable).map(x -> new SaleReportDTO(x));
		return result;
	}
}
