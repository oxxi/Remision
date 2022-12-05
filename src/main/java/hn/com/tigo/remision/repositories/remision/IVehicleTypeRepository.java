package hn.com.tigo.remision.repositories.remision;

import hn.com.tigo.remision.entities.remision.VehicleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVehicleTypeRepository extends JpaRepository<VehicleTypeEntity,Long> {
}
