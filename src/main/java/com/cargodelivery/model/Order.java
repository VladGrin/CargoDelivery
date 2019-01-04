package com.cargodelivery.model;

import java.util.Objects;

public class Order implements Comparable<Order>{
    private Integer id;
    private Integer userId;
    private String createDate;
    private String cityFrom;
    private String cityTo;
    private Type type;
    private Integer weight;
    private String startDate;
    private String endDate;
    private String recipient;
    private String recipientPhone;
    private String deliveryAddress;
    private Integer price;

    private Order(OrderBuilder builder) {
        this.userId = builder.userId;
        this.createDate = builder.createDate;
        this.cityFrom = builder.cityFrom;
        this.cityTo = builder.cityTo;
        this.type = builder.type;
        this.weight = builder.weight;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.recipient = builder.recipient;
        this.recipientPhone = builder.recipientPhone;
        this.price = builder.price;
        this.deliveryAddress = builder.deliveryAddress;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public Type getType() {
        return type;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public int compareTo(Order o) {
        return id - o.id;
    }

    public static class OrderBuilder {
        private Integer id;
        private Integer userId;
        private String createDate;
        private String cityFrom;
        private String cityTo;
        private Type type;
        private Integer weight;
        private String startDate;
        private String endDate;
        private String recipient;
        private String recipientPhone;
        private String deliveryAddress;
        private Integer price;

        public OrderBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setUserId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public OrderBuilder setCreateDate(String createDate) {
            this.createDate = createDate;
            return this;
        }

        public OrderBuilder setCityFrom(String cityFrom) {
            this.cityFrom = cityFrom;
            return this;
        }

        public OrderBuilder setCityTo(String cityTo) {
            this.cityTo = cityTo;
            return this;
        }

        public OrderBuilder setType(Type type) {
            this.type = type;
            return this;
        }

        public OrderBuilder setTypeByName(String typeName) {
            for (Type type : Type.values()) {
                if (type.name().equals(typeName)) {
                    this.type = type;
                }
            }
            return this;
        }

        public OrderBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public OrderBuilder setStartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public OrderBuilder setEndDate(String endDate) {
            this.endDate = endDate;
            return this;
        }

        public OrderBuilder setRecipient(String recipient) {
            this.recipient = recipient;
            return this;
        }

        public OrderBuilder setRecipientPhone(String recipientPhone) {
            this.recipientPhone = recipientPhone;
            return this;
        }

        public OrderBuilder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public OrderBuilder setPrice(Integer price) {
            this.price = price;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(createDate, order.createDate) &&
                Objects.equals(cityFrom, order.cityFrom) &&
                Objects.equals(cityTo, order.cityTo) &&
                type == order.type &&
                Objects.equals(weight, order.weight) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(endDate, order.endDate) &&
                Objects.equals(recipient, order.recipient) &&
                Objects.equals(recipientPhone, order.recipientPhone) &&
                Objects.equals(deliveryAddress, order.deliveryAddress) &&
                Objects.equals(price, order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, createDate, cityFrom, cityTo, type, weight, startDate, endDate, recipient, recipientPhone, deliveryAddress, price);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", createDate='" + createDate + '\'' +
                ", cityFrom='" + cityFrom + '\'' +
                ", cityTo='" + cityTo + '\'' +
                ", type=" + type +
                ", weight=" + weight +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", recipient='" + recipient + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", price=" + price +
                '}';
    }

    public enum Type {
        DOCUMENT,
        PARSEL,
        FREIGHT;
    }
}
