package org.generation.italy.repository;

import org.generation.italy.model.FotoForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoFormRepository extends JpaRepository<FotoForm, Integer> {

}
