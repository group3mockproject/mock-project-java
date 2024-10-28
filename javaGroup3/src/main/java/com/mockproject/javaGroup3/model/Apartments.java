package com.mockproject.javaGroup3.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Apartments")
public class Apartments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "apartment_id")
    private Long apartmentId;

    @ManyToOne
    @JoinColumn(name = "block_id")
    private Block block;

    @OneToMany(mappedBy = "apartment")
    private List<Debts> debts;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "delflag")
    private Boolean delFlag;
}
