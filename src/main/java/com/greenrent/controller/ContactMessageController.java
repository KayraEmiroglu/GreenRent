package com.greenrent.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenrent.domain.ContactMessage;
import com.greenrent.service.ContactMessageService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/contactmessage")
public class ContactMessageController {
	
	/*
	 * field injection neden zararli:
	 * servisten bir tane obje ürettik private olduğu için repositorye
	 * injectioni bilemez. Constructor injectionla bağımlılıkları gizlememiş
	 * oluruz.
	 */
	@Autowired
	private ContactMessageService contactMessageService;
	
	
	@PostMapping("/visitors")
	public ResponseEntity<Map<String,String>> createMessage(@Valid @RequestBody ContactMessage contactMessage){
		contactMessageService.createContactMessage(contactMessage);
		
		Map<String, String> map = new HashMap<>();
		map.put("message", "Contact Message Succesfully created");
		map.put("status", "true");
		
		return new ResponseEntity<>(map,HttpStatus.CREATED);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<ContactMessage>> getAllContactMessage(){
		 List<ContactMessage> list=contactMessageService.getAll();
		 return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ContactMessage> getMessage(@PathVariable("id") Long id){
		ContactMessage contactMessage=contactMessageService.getContactMessage(id);
		return ResponseEntity.ok(contactMessage);
	}
	
	@GetMapping("/request")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ContactMessage> getMessageWithRequestParam(@RequestParam("id") Long id){
		ContactMessage contactMessage=contactMessageService.getContactMessage(id);
		return ResponseEntity.ok(contactMessage);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String,String>> updateContactMessage(@PathVariable Long id,@RequestBody ContactMessage contactMessage){
		contactMessageService.updateContactMessagee(id, contactMessage);
		
		Map<String,String> map = new HashMap<>();
		map.put("message", "Contact Message Succesfully updated");
		map.put("status", "true");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Map<String,String>> deleteContactMessage(@PathVariable Long id){
		contactMessageService.deleteContactMessage(id);
		
		Map<String,String> map = new HashMap<>();
		map.put("message", "Contact Message Succesfully deleted");
		map.put("status", "true");
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("/pages")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Page<ContactMessage>> getAllWithPage(@RequestParam("page")int page,
																@RequestParam("size") int size,
																@RequestParam("sort")String prop,
																@RequestParam("direction")Direction direction){
		
		Pageable pageable=PageRequest.of(page, size,Sort.by(direction,prop));
		Page<ContactMessage> contactMessagePage=contactMessageService.getAllWithPage(pageable);
		
		return ResponseEntity.ok(contactMessagePage);
	}
	
}
