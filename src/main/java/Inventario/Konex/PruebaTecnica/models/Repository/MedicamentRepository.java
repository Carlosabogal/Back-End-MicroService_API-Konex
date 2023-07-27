package Inventario.Konex.PruebaTecnica.models.Repository;

import Inventario.Konex.PruebaTecnica.models.Entity.Medicament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
  Medicament save(Medicament medicament);
  Optional<Medicament> findById(Long id);
  void deleteById(Long id);
  Page<Medicament> findByNombreContainingAndLaboratorioContaining(String nombre, String laboratorio, Pageable pageable);


}