package com.cargodelivery.model;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private User user;
    private String cityFrom;
    private String cityTo;
    private Type type;
    private Integer weight;
    private String startDate;
    private String endDate;
    private String recipient;
    private String recipientPhone;

    private Order(OrderBuilder builder) {
        this.user = builder.user;
        this.cityFrom = builder.cityFrom;
        this.cityTo = builder.cityTo;
        this.type = builder.type;
        this.weight = builder.weight;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.recipient = builder.recipient;
        this.recipientPhone = builder.recipientPhone;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
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

    public Type getTypeByName() {

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

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public static class OrderBuilder {
        private Integer id;
        private User user;
        private String cityFrom;
        private String cityTo;
        private Type type;
        private Integer weight;
        private String startDate;
        private String endDate;
        private String recipient;
        private String recipientPhone;

        public OrderBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public OrderBuilder setUser(User user) {
            this.user = user;
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
            for(Type type : Type.values()){
                if (type.name().equals(typeName)){
                    this.type = type;
                }
            }
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

        public OrderBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }

    public enum Type {
        DOCUMENT,
        PARSEL,
        FREIGHT;
    }
}
