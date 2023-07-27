package Inventario.Konex.PruebaTecnica.models.Controller;

import Inventario.Konex.PruebaTecnica.models.Entity.Medicament;
import Inventario.Konex.PruebaTecnica.models.Service.MedicamentService;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/medicament")
public class MedicamentController {

    private final MedicamentService medicamentService;

    @Autowired
    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }


    @PostMapping
    public ResponseEntity<Medicament> createMedicament(@RequestBody Medicament medicament) {
        try {
            Medicament successMessage = medicamentService.CreateMedicament(medicament);
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception e) {
            return (ResponseEntity<Medicament>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public Medicament FindMedicant(@PathVariable long id) {
        return medicamentService.FindMedicamentById(id);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicamentById(@PathVariable Long id) {
        try {
            medicamentService.deleteMedicamentById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Medicament> updateMedicament(@PathVariable Long id, @RequestBody Medicament updatedMedicament) {
        try {
            Medicament existingMedicament = medicamentService.FindMedicamentById(id);
            if (existingMedicament == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            existingMedicament.setNombre(updatedMedicament.getNombre());
            existingMedicament.setLaboratorio(updatedMedicament.getLaboratorio());
            existingMedicament.setFechaFabricacion(updatedMedicament.getFechaFabricacion());
            existingMedicament.setFechaVencimiento(updatedMedicament.getFechaVencimiento());
            existingMedicament.setCantidadStock(updatedMedicament.getCantidadStock());
            existingMedicament.setValorUnitario(updatedMedicament.getValorUnitario());


            Medicament updatedMedicamentResult = medicamentService.updateMedicament(existingMedicament);
            return new ResponseEntity<>(updatedMedicamentResult, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity<Page<Medicament>> getMedicaments(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String laboratorio,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<Medicament> medicamentPage = medicamentService.getMedicaments(nombre, laboratorio, pageNumber, pageSize);
        return new ResponseEntity<>(medicamentPage, HttpStatus.OK);
    }

}