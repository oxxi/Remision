package hn.com.tigo.remision.repositories.remision;

import hn.com.tigo.remision.entities.remision.UnitOfMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurementEntity,Long> {
}
