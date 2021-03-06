package com.roncoo.eshop.cache.ha.model;

/**
 * @author 孙许
 * @version 1.0
 * @date 2021/6/27 16:08
 */
public class ProductInfo {

    private Long id;

    private String name;

    private Double price;

    private String pictureList;

    private String specification;

    private String service;

    private String color;

    private String size;

    private String modifiedTime;

    private Long cityId;

    private String cityName;

    private Long brandId;

    private String brandName;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", pictureList='" + pictureList + '\'' +
                ", specification='" + specification + '\'' +
                ", service='" + service + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", brandId=" + brandId +
                ", brandName='" + brandName + '\'' +
                ", shopId=" + shopId +
                '}';
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    private Long shopId;

    public ProductInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
        this.pictureList = pictureList;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

}
