package hn.com.tigo.remision.repositories.remision;

import hn.com.tigo.remision.entities.remision.ParameterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParameterRepository extends JpaRepository<ParameterEntity,Long> {

    ParameterEntity findByParameterName(String name);

}
