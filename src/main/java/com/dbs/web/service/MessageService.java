package com.dbs.web.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Message;
import com.dbs.web.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository repo;

	public Message findMessageById(String id)
	{
		try {
			Optional<Message> message = this.repo.findById(id);
			return message.orElseThrow(()->{
				
				return new EntityNotFoundException("Message with code"+id + " does not exist");
			});


		}catch(IllegalArgumentException e )
		{
			return null;
		}

	}

}
