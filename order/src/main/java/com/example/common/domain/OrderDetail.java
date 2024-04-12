package com.example.common.domain;


public class OrderDetail {

    private Long id;

    private String label;

    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }

}
