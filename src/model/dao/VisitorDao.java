package model.dao;

import java.util.List;

import model.entities.Visitor;

public interface VisitorDao {
	
	void insert(Visitor obj);
	void update(Visitor obj);
	void deleteById(Integer id);
	Visitor findById(Integer id);
	List<Visitor> findAll();
}
