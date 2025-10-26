package com.convergeX.technicalAssestement.technicalAssestement.repo;



import com.convergeX.technicalAssestement.technicalAssestement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,String> {
    List<Task>  findTop5ByOrderByCreatedTimeDesc();

}
