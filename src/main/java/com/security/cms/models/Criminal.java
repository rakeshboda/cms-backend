package com.security.cms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
        "id",
        "name",
        "age",
        "gender",
        "address",
        "crimes"
})

@Entity
public class Criminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Criminal name should not be empty.")
    @NotNull(message = "Criminal name should not be null.")
    private String name;
    @Positive(message = "Age should always positive.")
    @Max(value = 110,message = "Age should not greater than 110")
    private Integer age;
    @NotNull(message = "Gender should not null.")
    @Enumerated
    private Gender gender;
    @NotNull(message = "Address should not null.")
    @NotBlank(message = "Criminal name should not be empty.")
    private String address;

    @OneToMany(mappedBy = "criminal",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Crime> crimes;



    public Criminal() {
    }

    public Criminal(Long id, String name, Integer age, Gender gender, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public Criminal(List<Crime> crimes) {
        this.crimes = crimes;
    }

    public List<Crime> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<Crime> crimes) {
        this.crimes = crimes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
