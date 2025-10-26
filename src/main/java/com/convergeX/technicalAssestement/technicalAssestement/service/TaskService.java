package com.convergeX.technicalAssestement.technicalAssestement.service;


import com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto.RequestTaskDto;
import com.convergeX.technicalAssestement.technicalAssestement.entity.Task;

import java.util.List;

public interface TaskService {
    public void create(RequestTaskDto dto);
    public void delete(String dto) throws Exception;

    public List<Task> findLatestFiveByUser() ;

}
