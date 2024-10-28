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
@Table(name = "Utility_Payment")
public class UtilityPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "utility_id")
    private Utility utility;

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "delflag")
    private Boolean delFlag;
}