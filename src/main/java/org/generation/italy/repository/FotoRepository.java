package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Integer>  {

	List<Foto> findAllById(Integer id);

}
