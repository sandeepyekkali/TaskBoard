package com.example.taskboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskboard.domain.Tasks;
import com.example.taskboard.repos.Repo;

@Service
public class TaskService {

	@Autowired
	private Repo repo;
	
	public Tasks modTask(Tasks task) {
		
		if(task.getStatus()=="" || task.getStatus()==null) {
			task.setStatus("todo");
		}
		return repo.save(task);
	}
	
	public Tasks findOne(Long id) {
		
		return repo.getByPId(id);
	}
	
	public void deleteOne(Long id) {
		
		Tasks TS = findOne(id);
		repo.delete(TS);
	}
	
	public Iterable<Tasks> findAll() {
		return repo.findAll();	
	}
}
