package repository;
import modele.Client;






import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// la difference entre crud et jpa  crud-->save delete ..   jpa-->sort+tri+flush+pagination

public interface CustomerRepository extends JpaRepository<Client,Long> {
	
	@Query("from Client c where c.id = :id")
	Client findById(@Param("id") Long id);
	
	
	 Client save(Client c);
	
	
	// @Modifying(clearAutomatically = true)
	// @Query("update Client c set c.nom =:nom, c.prenom=:prenon where feedEntry.id =:id")
	// void update(@Param("nom") String nom,@Param("prenom") String prenom,@Param("id") Long id);
	 
	  Client saveAndFlush(Client c);
	 
	 public void delete(Client client); 
	 
}
