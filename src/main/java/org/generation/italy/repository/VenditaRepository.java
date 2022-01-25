package org.generation.italy.repository;

import org.generation.italy.model.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaRepository extends JpaRepository<Vendita, Integer> {

}
