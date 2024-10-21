package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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