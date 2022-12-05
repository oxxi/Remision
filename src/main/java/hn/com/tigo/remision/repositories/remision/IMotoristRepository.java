package hn.com.tigo.remision.repositories.remision;

import hn.com.tigo.remision.entities.remision.MotoristEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMotoristRepository extends JpaRepository<MotoristEntity,Long> {
}
