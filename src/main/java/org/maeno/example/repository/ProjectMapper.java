package org.maeno.example.repository;

import org.apache.ibatis.annotations.Mapper;
import org.maeno.example.domain.Project;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> list();

    List<Project> selectProjectByChecked(List<Integer> ids);
}
