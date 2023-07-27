package Inventario.Konex.PruebaTecnica.models.Service;

import Inventario.Konex.PruebaTecnica.models.Entity.Medicament;
import Inventario.Konex.PruebaTecnica.models.Repository.MedicamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentService {
    private final MedicamentRepository medicamentRepository;

    @Autowired
    public MedicamentService(MedicamentRepository medicamentRepository) {
        this.medicamentRepository = medicamentRepository;
    }

    public Medicament CreateMedicament(Medicament medicament) {
        medicamentRepository.save(medicament);

        return medicament;
    }

    public Medicament FindMedicamentById(Long id) {

        Optional<Medicament> medicament = medicamentRepository.findById(id);

        return medicament.get();
    }

    public void deleteMedicamentById(Long id) {
        medicamentRepository.deleteById(id);
    }

    public Medicament updateMedicament(Medicament updatedMedicament) {
        return medicamentRepository.save(updatedMedicament);
    }

    public Page<Medicament> getMedicaments(String nombre, String laboratorio, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return medicamentRepository.findByNombreContainingAndLaboratorioContaining(nombre, laboratorio, pageable);
    }


}
