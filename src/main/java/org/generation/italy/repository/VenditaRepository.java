package org.generation.italy.repository;

import org.generation.italy.model.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaRepository extends JpaRepository<Vendita, Integer> {
	
	@Query(value ="select sum(vp.quantita * p.prezzo_vendita) as incasso_totale\r\n"
			+ "from vendita_prodotto vp \r\n"
			+ "join prodotto p \r\n"
			+ "on vp.prodotto_id = p.id\r\n"
			+ "join vendita v \r\n"
			+ "on vp.vendita_id = v.id \r\n"
			+ "where MONTH(v.data_vendita) = MONTH(now())  and YEAR(v.data_vendita) = YEAR(now())  ", 
			  nativeQuery = true)
	public  Integer getTotaleVendite();

}

