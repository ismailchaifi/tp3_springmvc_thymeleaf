package ma.enset.hospital.repositories;

import ma.enset.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PatientRepository extends JpaRepository<Patient, String> {
    @Transactional
    void deletePatientById(String id);
}
