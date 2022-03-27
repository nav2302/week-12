package com.greatlearning.week12assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.week12assignment.exception.ItemNotFoundException;
import com.greatlearning.week12assignment.model.Item;
import com.greatlearning.week12assignment.repository.ItemRepository;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	@Override
	public Item findItemById(long id) {
		// TODO Auto-generated method stub
		return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item not found"));
	}

	@Override
	public Item save(Item item) {
		// TODO Auto-generated method stub
		return itemRepository.save(item);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		itemRepository.deleteById(id);
	}

}
