package hn.com.tigo.remision.repositories.remision;

import hn.com.tigo.remision.entities.remision.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository extends JpaRepository<LocationEntity,Long> {
    LocationEntity findByshortName(String name);
}
