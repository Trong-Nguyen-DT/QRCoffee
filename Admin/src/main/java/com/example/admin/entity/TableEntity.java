package com.example.admin.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "tables")
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean status;

    @OneToMany(mappedBy = "tableEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orderEntities;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
