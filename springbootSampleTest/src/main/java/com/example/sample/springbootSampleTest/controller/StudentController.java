package com.example.sample.springbootSampleTest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.springbootSampleTest.entity.Student;
import com.example.sample.springbootSampleTest.repository.StudentRepository;


@RestController
public class StudentController {
	
	@Autowired
	StudentRepository stdRepo;
	
	
	@PostMapping("/api/saveit")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(stdRepo.save(student),HttpStatus.CREATED);
	}
	
	@GetMapping("/api/getit")
	public ResponseEntity<List<Student>> getStudent(){
		return new ResponseEntity<>(stdRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/api/getit/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id){
		
		Optional<Student> student =stdRepo.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(),HttpStatus.OK);
		}
		else {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/updateit/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student stud){
		
		Optional<Student> student =stdRepo.findById(id);
		if(student.isPresent()) {
			student.get().setsName(stud.getsName());
			student.get().setsEmail(stud.getsEmail());
			student.get().setsAddress(stud.getsAddress());
			return new ResponseEntity<>(stdRepo.save(student.get()),HttpStatus.OK);
		}
		else {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/api/del/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id){
		
		Optional<Student> student =stdRepo.findById(id);
		if(student.isPresent()) {
			stdRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

}
