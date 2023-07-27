package Inventario.Konex.PruebaTecnica.models.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import Inventario.Konex.PruebaTecnica.models.Entity.Venta;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByFechaHoraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
