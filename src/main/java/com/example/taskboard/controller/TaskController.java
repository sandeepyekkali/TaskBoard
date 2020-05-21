package com.example.taskboard.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskboard.domain.Tasks;
import com.example.taskboard.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskService ts;
	
	@GetMapping("")
	public String getHome(){
		return "Welcome";
	}
	
	@PostMapping("")
	public ResponseEntity<?> modifyTask(@Valid @RequestBody Tasks task, BindingResult res){
		
		if(res.hasErrors()) {
			Map<String,String> errorsMap = new HashMap<>();
			
			for(FieldError e : res.getFieldErrors()) {
				errorsMap.put(e.getField(),e.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(errorsMap, HttpStatus.BAD_REQUEST);
		}
		
		Tasks newTask = ts.modTask(task);
		return new ResponseEntity<Tasks>(newTask, HttpStatus.CREATED);
	}
	
	@GetMapping("/list")
	public Iterable<Tasks> getAllTasks(){
		return ts.findAll();
	}
	
	@GetMapping("/{task_id}")
	public ResponseEntity<Tasks> getOneTask(@PathVariable long task_id){
		//Tasks T = ts.findOne(task_id);
		return new ResponseEntity<Tasks>(ts.findOne(task_id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{task_id}")
	public ResponseEntity<?> deleteOneTask(@PathVariable long task_id){
	   ts.deleteOne(task_id);
	   return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}
}
