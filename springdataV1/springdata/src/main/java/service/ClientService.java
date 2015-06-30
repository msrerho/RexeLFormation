package service;

import modele.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repository.CustomerRepository;

@Service
public class ClientService {
	
	@Autowired  // injecte explicitement un bean
	private CustomerRepository clientRepository;

	@Transactional
	public Client create(Client  client){
		
		return clientRepository.save(client);
	}
	
	@Transactional
	public Client findById(Long id) {
		return clientRepository.findById(id);
}
	@Transactional
	public Client update(Client client) {
		return clientRepository.saveAndFlush(client);
}
	
	
	
}

