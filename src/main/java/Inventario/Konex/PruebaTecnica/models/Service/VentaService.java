package Inventario.Konex.PruebaTecnica.models.Service;

import Inventario.Konex.PruebaTecnica.models.DTO.VentaDTO;
import Inventario.Konex.PruebaTecnica.models.Entity.Medicament;
import Inventario.Konex.PruebaTecnica.models.Entity.Venta;
import Inventario.Konex.PruebaTecnica.models.Repository.MedicamentRepository;
import Inventario.Konex.PruebaTecnica.models.Repository.VentaRepository;
import jakarta.transaction.Transactional;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final MedicamentRepository medicamentRepository;

    @Autowired
    public VentaService(VentaRepository ventaRepository, MedicamentRepository medicamentRepository) {
        this.ventaRepository = ventaRepository;
        this.medicamentRepository = medicamentRepository;
    }



    public Venta realizarVenta(Long medicamentoId, int cantidad) throws Exception {
        Medicament medicamento = medicamentRepository.findById(medicamentoId).orElse(null);

        if (medicamento == null) {

            throw new Exception("Medicamento con ID " + medicamentoId + " no encontrado.");
        }
        double valorTotal = cantidad * medicamento.getValorUnitario();
        medicamento.setCantidadStock(medicamento.getCantidadStock() - cantidad);
        Venta venta = new Venta();
        venta.setMedicamento(medicamento);
        venta.setCantidad(cantidad);
        venta.setValorUnitario(BigDecimal.valueOf(medicamento.getValorUnitario()));
        venta.setValorTotal(BigDecimal.valueOf(valorTotal));
        venta.setFechaHora(LocalDateTime.now());

        ventaRepository.save(venta);

        medicamentRepository.save(medicamento);

        return venta;
    }

    public Venta actualizarVenta(Long ventaId, VentaDTO ventaRequest) throws Exception {

        Venta venta = ventaRepository.findById(ventaId).orElse(null);

        if (venta == null) {
            throw new Exception("Venta con ID " + ventaId + " no encontrada.");
        }


        Medicament medicamento = venta.getMedicamento();


        venta.setMedicamento(medicamento);
        venta.setCantidad(ventaRequest.getCantidad());
        venta.setValorUnitario(BigDecimal.valueOf(ventaRequest.getValorUnitario()));
        venta.setValorTotal(BigDecimal.valueOf(ventaRequest.getValorTotal()));
        venta.setFechaHora(LocalDateTime.now());


        return ventaRepository.save(venta);
    }

    public void eliminarVenta(Long ventaId) throws Exception {

        Venta venta = ventaRepository.findById(ventaId).orElse(null);

        if (venta == null) {
            throw new Exception("Venta con ID " + ventaId + " no encontrada.");
        }

        Medicament medicamento = venta.getMedicamento();


        medicamento.setCantidadStock(medicamento.getCantidadStock() + venta.getCantidad());


        ventaRepository.deleteById(ventaId);
    }

    public List<Venta> obtenerVentasPorRangoDeFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return ventaRepository.findByFechaHoraBetween(fechaInicio, fechaFin);
    }
}
