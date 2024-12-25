package com.luv2code.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="sku")
    private String sku;
    @ManyToOne
    @JoinColumn(name="category_id",nullable = false)
    private ProductCategory category;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="unit_price")
    private BigDecimal unitPrice;
    @Column(name="image_url")
    private String imageUrl;
    @Column(name="active")
    private Boolean active;
    @Column(name="units_in_stock")
    private int unitsInStock;
    @Column(name="date_created")
    private Date daeCreated;
    @Column(name="last_updated")
    private Date lastUpdated;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getDaeCreated() {
        return daeCreated;
    }

    public void setDaeCreated(Date daeCreated) {
        this.daeCreated = daeCreated;
    }*/
}
