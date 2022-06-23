package com.example.dpms_research_sample;

import javax.persistence.*;

@Entity
@Table(name = "bstdb")
public class BST {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @Column(name = "bloodsugar",nullable = false, length = 15)
    private float bloodsugar;

    @Column(name = "date",nullable = false, length = 25)
    private String date;

    @Column(name = "time",nullable = false, length = 25)
    private String time;

    @Column(name = "period",nullable = false, length = 45)
    private String period;

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

    public float getBloodsugar() {
        return bloodsugar;
    }

    public void setBloodsugar(float bloodsugar) {
        this.bloodsugar = bloodsugar;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}
