package test;

import java.util.Iterator;

import modele.Client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import repository.CustomerRepository;
import datasource.BeanConfiguration;

public class CustomerMaster {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				BeanConfiguration.class);
		
		CustomerRepository repository = context.getBean(CustomerRepository.class);
		 
		//repository.save(new Client("A1", "A2"));
	    //repository.save(new Client("A2", "A2"));
		Client c=repository.findById(new Long(7));
		
		c.setNom("saida");
	//repository.update(c.getNom(),c.getPrenom(),c.getMatricule());
		repository.saveAndFlush(c);
	System.out.println(c.getNom()+" "+c.getPrenom());
		
		/*Iterator<Client> customers =  repository.findAll().iterator();
		while (customers.hasNext()) {
			Client c=customers.next();
			
		   System.out.println(c.getNom()+" "+ c.getPrenom()) ;  
		
		}*/
		
		((AbstractApplicationContext) context).close();
	
	}

}
