package org.maeno.example.service;

import org.maeno.example.domain.Project;
import org.maeno.example.repository.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> list() {
        return projectMapper.list();
    }

}
