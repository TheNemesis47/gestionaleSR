package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private String method;

    @Column(name = "transaction_id")
    private String transactionId;

    private Double amount;

    private String currency;

    private String status;

    private LocalDateTime date;
}
