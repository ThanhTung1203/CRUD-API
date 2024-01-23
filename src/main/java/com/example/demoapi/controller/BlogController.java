package com.example.demoapi.controller;

import com.example.demoapi.model.Blog;
import com.example.demoapi.model.Category;
import com.example.demoapi.repository.BlogRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@CrossOrigin("*") //cho phép truy cập bới ứng dụng khác
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    BlogRepository blogController;
    @Autowired
    BlogRepository blogRepository;


    @GetMapping
    public ResponseEntity findALl() {
        return new ResponseEntity<>(blogController.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Blog blog, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String err = "";
            for (ObjectError o : bindingResult.getAllErrors()
            ) {
                err += o.getDefaultMessage() + "\n";
            }
            return new ResponseEntity(err, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(blogController.save(blog), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity save(@RequestBody Blog blog, @PathVariable Long id) {
        blog.setId(id);
        return new ResponseEntity(blogController.save(blog), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity showEdit( @PathVariable Long id) {
        return new ResponseEntity(blogController.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        blogController.deleteById(id);
        return new ResponseEntity("delete done", HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Blog>> findByKeyword(@RequestParam String keyword) {
        List<Blog> blogs = blogRepository.findByTitleContainingOrContentContaining(keyword, keyword);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/searchCate/{id}")
    public ResponseEntity findByKeyword1(@PathVariable Long id) {
        List<Blog> list = blogRepository.findAllByCategoryIdCate(id);
        return new ResponseEntity<>(blogRepository.findAllByCategoryIdCate(id), HttpStatus.OK);
    }
}
