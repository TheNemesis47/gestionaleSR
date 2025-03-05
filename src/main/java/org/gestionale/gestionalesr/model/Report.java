package org.gestionale.gestionalesr.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportType;  // Tipo di report (es. "Vendite", "Inventario", "Clienti")
    private LocalDateTime generatedAt;  // Data di generazione del report

    @Lob
    private String reportData;  // Dati del report in formato JSON o CSV
}
