package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {

	// TODO work in progress
	List<Visita> findByPercorsoId(Integer id);

}
