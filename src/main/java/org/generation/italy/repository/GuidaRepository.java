package org.generation.italy.repository;

import org.generation.italy.model.Guida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidaRepository extends JpaRepository<Guida, Integer> {

}
