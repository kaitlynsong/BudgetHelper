/* ICS4U Software Development Project
 * 
 * Service class for Category that delegates all the calls to CategoryRepository. 
 * 
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Expense;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public List<Category> listAll() {
		return categoryRepo.findAll();
	}
	
	public void save(Category category) {
		categoryRepo.save(category);
	}
	
	public Category get(Long id) {
		return categoryRepo.findById(id).get();
	}
	
	public void delete(Long id) {
		categoryRepo.deleteById(id);
	}
	
}
