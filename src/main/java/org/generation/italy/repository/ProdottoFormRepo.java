package org.generation.italy.repository;

import org.generation.italy.model.ProdottoForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProdottoFormRepo extends JpaRepository<ProdottoForm, Integer> {

}
