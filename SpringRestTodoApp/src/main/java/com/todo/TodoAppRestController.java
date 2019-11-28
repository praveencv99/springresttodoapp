package com.todo;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Transactional
@RequestMapping("/api")
public class TodoAppRestController {

	@Autowired
	TodoAppRepository todoAppRepository;

	@GetMapping("/tasks")
	@Transactional
	public List<Task> getTodoList() {

		return todoAppRepository.findAll();
	}

	@PostMapping("/task")
	@Transactional
	public Task insertTodo(@RequestBody Task task) {
		return todoAppRepository.saveAndFlush(task);
	}

	@PutMapping("/task/{id}")
	@Transactional
	public Task updateTodo(@PathVariable("id") Long id, @RequestBody Task task) {
		Task orginalTask = todoAppRepository.getOne(id);
		String modifiedTitle;
		modifiedTitle = task.getTitle();
		orginalTask.setTitle(modifiedTitle);
		return todoAppRepository.saveAndFlush(orginalTask);
	}

	@DeleteMapping("/task/{id}")
	@Transactional
	public void daleteTodo(@PathVariable Long id) {
		Task task = todoAppRepository.getOne(id);
		todoAppRepository.delete(task);
	}

}
