package com.example.demoapi.controller;

import com.example.demoapi.model.Blog;
import com.example.demoapi.model.Category;
import com.example.demoapi.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryController {
  @Autowired
    CategoryRepository categoryRepository;

  @GetMapping
    public ResponseEntity findAll(){
      return  new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
  }
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody  Category category , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String err = "";
            for (ObjectError o:bindingResult.getAllErrors()
            ) { err += o.getDefaultMessage() + "\n";
            }
            return  new ResponseEntity(err,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(categoryRepository.save(category),HttpStatus.OK);
    }

}
