package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.VenditaProdotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaProdottoRepository extends JpaRepository<VenditaProdotto, Integer> {

	List<VenditaProdotto> findByVenditaId(Integer id);

}
