package org.generation.italy.repository;

import org.generation.italy.model.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaRepository extends JpaRepository<Vendita, Integer> {
	
	@Query(value ="select coalesce(sum(vp.quantita * p.prezzo_vendita),0) as incasso_totale\r\n"
			+ "from vendita_prodotto vp \r\n"
			+ "join prodotto p \r\n"
			+ "on vp.prodotto_id = p.id\r\n"
			+ "join vendita v \r\n"
			+ "on vp.vendita_id = v.id \r\n"
			+ "where v.data_vendita > date_sub(CURDATE() ,interval 1 month) ", 
			  nativeQuery = true)
	public  Integer getTotaleVendite();

}

