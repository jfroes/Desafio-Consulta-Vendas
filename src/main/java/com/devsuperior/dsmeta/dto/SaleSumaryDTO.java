package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSumaryProjection;

public class SaleSumaryDTO {
    private String sellerName;
    private double total;

    public SaleSumaryDTO() {}

    public SaleSumaryDTO(SaleSumaryProjection projection){
        sellerName = projection.getSellerName();
        total = projection.getTotal();
    }

    public SaleSumaryDTO(String sellerName, double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public double getTotal() {
        return total;
    }
}
