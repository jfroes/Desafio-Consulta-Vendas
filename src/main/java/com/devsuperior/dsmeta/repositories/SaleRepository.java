package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleReportProjection;
import com.devsuperior.dsmeta.projections.SaleSumaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT TB_SELLER.name sellerName, SUM(TB_SALES.amount) total "
            +"FROM TB_SELLER "
            +"INNER JOIN TB_SALES ON TB_SELLER.id = TB_SALES.seller_id "
            +"WHERE TB_SALES.date BETWEEN :minDate AND :maxDate "
            +"GROUP BY TB_SELLER.name ")
    List<SaleSumaryProjection> salesSumary(LocalDate minDate, LocalDate maxDate);

    @Query(nativeQuery = true,
            value = "SELECT TB_SALES.id, TB_SALES.date, TB_SALES.amount, TB_SELLER.name "
            +"FROM TB_SALES "
            +"INNER JOIN TB_SELLER ON TB_SALES.seller_id = TB_SELLER.id "
            +"WHERE UPPER(TB_SELLER.name) LIKE CONCAT('%', UPPER(:name), '%') "
                    + "AND TB_SALES.date BETWEEN  :minDate AND :maxDate",
            countQuery = "SELECT COUNT(*) FROM TB_SALES "
                    +"INNER JOIN TB_SELLER ON TB_SALES.seller_id = TB_SELLER.id "
                    + "WHERE UPPER(TB_SELLER.name) LIKE CONCAT('%', UPPER(:name), '%') "
                    + "AND TB_SALES.date BETWEEN  :minDate AND :maxDate")
    Page<SaleReportProjection> salesReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

}
