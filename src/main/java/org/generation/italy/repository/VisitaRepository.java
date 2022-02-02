package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Visita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitaRepository extends JpaRepository<Visita, Integer> {

	public List<Visita> findByPercorsoId(Integer id);
	
	@Query(value = "SELECT * FROM visita v\r\n"
			+ "WHERE v.percorso_id = :id\r\n"
			+ "AND (`data` = CURDATE()\r\n"
			+ "AND orario > DATE_ADD(NOW(), INTERVAL 2 HOUR))\r\n"
			+ "OR v.percorso_id = :id\r\n"
			+ "AND `data` > CURDATE()\r\n"
			+ "ORDER BY `data`, orario;",
			nativeQuery = true)
	public List<Visita> getVisitaNotLessThen2h(@Param("id") Integer id);
	
	@Query(value = "SELECT * FROM visita\r\n"
			+ "WHERE `data` = CURDATE()\r\n"
			+ "AND orario > NOW()\r\n"
			+ "OR `data` > CURDATE()\r\n"
			+ "ORDER BY `data`, orario;",
			nativeQuery = true)
	public List<Visita> findAllNotPastOrderByDataOrario();
	
}
