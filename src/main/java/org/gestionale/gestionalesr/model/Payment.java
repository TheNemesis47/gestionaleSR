package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "payment")
    private Order order;

    private String paymentMethod;  // Metodo di pagamento (es. "Contanti", "Carta", "PayPal")
    private String transactionId;  // ID transazione se digitale
    private Double amount;  // Importo pagato
    private LocalDateTime paymentDate;  // Data del pagamento
}
