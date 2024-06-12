package com.match.domain.entity;
// Класс служит для того, что бы передавать значение uid в абстрактный класс репозитория
public abstract class BaseEntity {
    protected String uid;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }
}
