package org.generation.italy.repository;

import org.generation.italy.model.Percorso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PercorsoRepository extends JpaRepository<Percorso, Integer> {

}
