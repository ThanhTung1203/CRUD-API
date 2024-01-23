package com.example.demoapi.repository;

import com.example.demoapi.model.Blog;
import com.example.demoapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {


    List<Blog> findByTitleContainingOrContentContaining(String keyword, String keyword1);

    List<Blog> findAllByCategoryIdCate(Long id);
}
