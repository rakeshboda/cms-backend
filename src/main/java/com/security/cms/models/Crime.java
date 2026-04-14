package com.security.cms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Crime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String crimeDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criminal_id",nullable = false)
    @JsonIgnore
    private Criminal criminal;

    public Crime() {
    }

    public Crime(String crimeDetail, Criminal criminal) {
        this.crimeDetail = crimeDetail;
        this.criminal = criminal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrimeDetail() {
        return crimeDetail;
    }

    public void setCrimeDetail(String crimeDetail) {
        this.crimeDetail = crimeDetail;
    }

    public Criminal getCriminal() {
        return criminal;
    }

    public void setCriminal(Criminal criminal) {
        this.criminal = criminal;
    }
}
