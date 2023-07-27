package Inventario.Konex.PruebaTecnica.models.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicamento_id")
    private Medicament medicamento;

    private int cantidad;
    private BigDecimal valorUnitario;
    private BigDecimal valorTotal;
    private LocalDateTime fechaHora;

    public Venta() {
    }

}