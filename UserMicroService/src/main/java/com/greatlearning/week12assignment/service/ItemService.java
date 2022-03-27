package com.greatlearning.week10assignment.service;

import java.util.List;

import com.greatlearning.week10assignment.model.Item;

public interface ItemService {
	List<Item> getAllItems();

	Item findItemById(long id);

	Item save(Item item);

	void delete(long id);
}
