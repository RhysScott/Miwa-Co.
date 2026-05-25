package org.miwa.miwa.service;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.Person;
import org.miwa.miwa.exception.BusinessException;
import org.miwa.miwa.mapper.PeopleMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeopleService {

    private final PeopleMapper peopleMapper;

    public List<Person> list() {
        return peopleMapper.findAll();
    }

    public Person getById(Long id) {
        Person p = peopleMapper.findById(id);
        if (p == null) throw new BusinessException(404, "成员不存在");
        return p;
    }

    public Person create(Person person) {
        if (person.getSortOrder() == null) person.setSortOrder(0);
        peopleMapper.insert(person);
        return person;
    }

    public Person update(Long id, Person person) {
        Person existing = getById(id);
        if (person.getName() != null) existing.setName(person.getName());
        if (person.getEn() != null) existing.setEn(person.getEn());
        if (person.getRole() != null) existing.setRole(person.getRole());
        if (person.getRoleEn() != null) existing.setRoleEn(person.getRoleEn());
        if (person.getBio() != null) existing.setBio(person.getBio());
        if (person.getImage() != null) existing.setImage(person.getImage());
        if (person.getContent() != null) existing.setContent(person.getContent());
        if (person.getSortOrder() != null) existing.setSortOrder(person.getSortOrder());
        peopleMapper.update(existing);
        return existing;
    }

    public void delete(Long id) {
        getById(id);
        peopleMapper.deleteById(id);
    }

    public void updateSort(SortRequest request) {
        List<Person> items = new ArrayList<>();
        for (SortRequest.SortItem si : request.getItems()) {
            Person p = new Person();
            p.setId(si.getId());
            p.setSortOrder(si.getSortOrder());
            items.add(p);
        }
        peopleMapper.updateSortOrders(items);
    }
}
