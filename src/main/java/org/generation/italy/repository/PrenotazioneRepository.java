package org.generation.italy.repository;

import java.util.List;

import org.generation.italy.model.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer> {
	
//	@Query("select sum(p2.quantita * p.prezzo)  \r\n"
//	+ "from percorso p \r\n"
//	+ "join visita v \r\n"
//	+ "on v.percorso_id = p.id \r\n"
//	+ "join prenotazione p2 \r\n"
//	+ "on p2.visita_id =v.id ")
//	public  Integer getTotalePrenotazioni();
	
	@Query(value = "select coalesce(sum(p2.quantita * p.prezzo),0)\r\n"
			+ "from visita v\r\n"
			+ "join  percorso p \r\n"
			+ "on v.percorso_id = p.id \r\n"
			+ "join prenotazione p2 \r\n"
			+ "on p2.visita_id =v.id \r\n"
			+ "where v.`data` > date_sub(CURDATE() ,interval 1 month)", 
			  nativeQuery = true)
	public Integer getTotalePrenotazioni();

//	@Query(value = "SELECT SUM(p.quantita)\r\n"
//			+ "FROM visita v\r\n"
//			+ "JOIN prenotazione p\r\n"
//			+ "ON p.visita_id = v.id\r\n"
//			+ "WHERE v.id = :id;",
//			  nativeQuery = true)
//	public List<Prenotazione> getTotalePrenotazioniById(@Param("id") Integer id);

}
