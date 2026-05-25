package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.Project;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectMapper projectMapper;

    public List<Project> list() {
        return projectMapper.findAll();
    }

    public Project getById(Long id) {
        Project p = projectMapper.findById(id);
        if (p == null) throw new BusinessException(404, "项目不存在");
        return p;
    }

    public Project create(Project project) {
        if (project.getSortOrder() == null) project.setSortOrder(0);
        projectMapper.insert(project);
        return project;
    }

    public Project update(Long id, Project project) {
        Project existing = getById(id);
        if (project.getZh() != null) existing.setZh(project.getZh());
        if (project.getEn() != null) existing.setEn(project.getEn());
        if (project.getDesc() != null) existing.setDesc(project.getDesc());
        if (project.getImage() != null) existing.setImage(project.getImage());
        if (project.getContent() != null) existing.setContent(project.getContent());
        if (project.getSortOrder() != null) existing.setSortOrder(project.getSortOrder());
        projectMapper.update(existing);
        return existing;
    }

    public void delete(Long id) {
        getById(id);
        projectMapper.deleteById(id);
    }

    public void updateSort(SortRequest request) {
        List<Project> items = new ArrayList<>();
        for (SortRequest.SortItem si : request.getItems()) {
            Project p = new Project();
            p.setId(si.getId());
            p.setSortOrder(si.getSortOrder());
            items.add(p);
        }
        projectMapper.updateSortOrders(items);
    }
}
