package com.mockproject.javaGroup3.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Debts")
public class Debts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "debt_id")
    private Long debtId;

    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartments apartment;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "description")
    private String description;

    @Column(name = "delflag")
    private Boolean delFlag;
}