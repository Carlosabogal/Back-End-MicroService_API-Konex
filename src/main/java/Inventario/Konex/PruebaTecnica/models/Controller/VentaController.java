package Inventario.Konex.PruebaTecnica.models.Controller;

import Inventario.Konex.PruebaTecnica.models.DTO.VentaDTO;
import Inventario.Konex.PruebaTecnica.models.Entity.Venta;
import Inventario.Konex.PruebaTecnica.models.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/venta")
public class VentaController {


    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping
    public Venta realizarVenta(@RequestParam Long medicamentoId, @RequestParam int cantidad) throws Exception {

        return ventaService.realizarVenta(medicamentoId, cantidad);
    }
    @PutMapping("/{id}")
    public Venta actualizarVenta(@PathVariable Long id, @RequestBody VentaDTO ventaRequestDTO) throws Exception {
        return ventaService.actualizarVenta(id, ventaRequestDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable Long id) throws Exception {
        ventaService.eliminarVenta(id);
    }
    @GetMapping("/filtrar")
    public List<Venta> obtenerVentasPorRangoDeFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin
    ) {
        return ventaService.obtenerVentasPorRangoDeFechas(fechaInicio, fechaFin);
    }
}
