package com.greenrent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.greenrent.domain.ContactMessage;
import com.greenrent.exception.ResourceNotFoundException;
import com.greenrent.exception.message.ErrorMessage;
import com.greenrent.repository.ContactMessageRepository;

import lombok.AllArgsConstructor;

//Constructor injection 
@AllArgsConstructor
@Service
public class ContactMessageService {
	
	//spring container'dan almak için
	@Autowired
	private ContactMessageRepository repository;
	
	public void createContactMessage(ContactMessage contactMessage) {
		repository.save(contactMessage);
	}
	
	public List<ContactMessage> getAll(){
		return repository.findAll();
	}
	
	public ContactMessage getContactMessage(Long id) throws ResourceNotFoundException {
		return
		repository.findById(id).orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_MESSAGE, id)));
	}
	
	public void deleteContactMessage(Long id) throws ResourceNotFoundException {
		ContactMessage message = getContactMessage(id);
		repository.deleteById(message.getId());
	}
	
	public void updateContactMessagee(Long id,ContactMessage newContactMessage) {
		ContactMessage foundMessage = getContactMessage(id);
		
		foundMessage.setName(newContactMessage.getName());
		foundMessage.setEmail(newContactMessage.getEmail());
		foundMessage.setSubject(newContactMessage.getSubject());
		foundMessage.setBody(newContactMessage.getBody());
		
		repository.save(foundMessage);
	}
	
	public Page<ContactMessage> getAllWithPage(Pageable pageable){
		return repository.findAll(pageable);
	}

}
