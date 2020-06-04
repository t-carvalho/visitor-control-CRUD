package model.dao;

import java.util.List;

public interface Dweller {
	
	void insert(Dweller obj);
	void update(Dweller obj);
	void deleteById(Integer id);
	Dweller findById(Integer id);
	List<Dweller> findAll();
}
