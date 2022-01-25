package org.generation.italy.repository;

import org.generation.italy.model.AcquistoProdotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoProdottoRepository extends JpaRepository<AcquistoProdotto, Integer>{

}
