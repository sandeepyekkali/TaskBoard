package com.example.taskboard.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.taskboard.domain.Tasks;

@Repository
public interface Repo extends CrudRepository<Tasks, Long> {
   
    Tasks getByPId(Long id);
}
