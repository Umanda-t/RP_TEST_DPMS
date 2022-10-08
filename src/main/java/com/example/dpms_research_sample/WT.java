package com.example.dpms_research_sample;

import javax.persistence.*;

@Entity
@Table(name = "wtdb")
public class WT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @Column(name = "weight",nullable = false, length = 15)
    private float weight;

    @Column(name = "height",nullable = false, length = 15)
    private double height;

    @Column(name = "date",nullable = false, length = 35)
    private String date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
