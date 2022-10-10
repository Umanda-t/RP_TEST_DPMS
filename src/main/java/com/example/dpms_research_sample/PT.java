package com.example.dpms_research_sample;

import javax.persistence.*;
@Entity
@Table(name = "ptdb")
public class PT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    private User username;

    @Column(name = "systolic",nullable = false, length = 15)
    private float systolic;

    @Column(name = "diastolic",nullable = false, length = 15)
    private float diastolic;

    @Column(name = "pulse",nullable = false, length = 15)
    private float pulse;

    @Column(name = "date",nullable = false, length = 25)
    private String date;

    @Column(name = "time",nullable = false, length = 25)
    private String time;

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

    public float getSystolic() {
        return systolic;
    }

    public void setSystolic(float systolic) {
        this.systolic = systolic;
    }

    public float getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(float diastolic) {
        this.diastolic = diastolic;
    }

    public float getPulse() {
        return pulse;
    }

    public void setPulse(float pulse) {
        this.pulse = pulse;
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
}
