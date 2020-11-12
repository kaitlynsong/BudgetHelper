/* ICS4U Software Development Project
 * 
 * Service class for Income that delegates all the calls to IncomeRepository. Also provides some
 * helper functions for complicated business logic. 
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper.Income;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepo;

	public List<Income> listAll() {
		return incomeRepo.findAll();
	}

	public Double getTotalIncome(String budgetMonth) {
		List<Income> incomeEntries = incomeRepo.findByDateStartingWith(budgetMonth);
		Double totalIncome = 0.0;

		for (int i = 0; i < incomeEntries.size(); i++) {
			Double income = incomeEntries.get(i).getAmount();
			totalIncome = totalIncome + income;
		}

		return totalIncome;
	}

	public void save(Income income) {
		incomeRepo.save(income);
	}

	public Income get(Long id) {
		return incomeRepo.findById(id).get();
	}

	public void delete(Long id) {
		incomeRepo.deleteById(id);
	}

}