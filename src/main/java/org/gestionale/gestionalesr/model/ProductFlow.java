package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.*;
import org.gestionale.gestionalesr.enums.MovementType;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_flows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "movement_date")
    private LocalDateTime movementDate;

    @Column(name = "product_name")
    private String productName;

    private Integer quantity;

    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private MovementType movementType; // ENUM: IN, OUT

    private String reason; // Es. Restock, Order, Return
}
