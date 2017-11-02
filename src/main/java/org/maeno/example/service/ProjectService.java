package org.maeno.example.service;

import org.maeno.example.domain.Project;
import org.maeno.example.repository.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> list() {
        return projectMapper.list();
    }

    public List<Project> selectProjectByChecked(ArrayList<Project> projectList) {
        final List<Integer> ids = new ArrayList<>();
                projectList.stream()
                        .filter(project -> project.getChecked())
                        .forEach(project -> ids.add(project.getId()));
        return projectMapper.selectProjectByChecked(ids);
    }
}
