package org.miwa.miwa.controller.admin;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.dto.SortRequest;
import org.miwa.miwa.entity.Person;
import org.miwa.miwa.service.PeopleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminPeopleController {

    private final PeopleService peopleService;

    @PostMapping("/people")
    public Result<Person> create(@RequestBody Person person) {
        return Result.ok(peopleService.create(person));
    }

    @PutMapping("/people/{id}")
    public Result<Person> update(@PathVariable Long id, @RequestBody Person person) {
        return Result.ok(peopleService.update(id, person));
    }

    @DeleteMapping("/people/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        peopleService.delete(id);
        return Result.ok();
    }

    @PutMapping("/people/sort")
    public Result<Void> sort(@RequestBody SortRequest request) {
        peopleService.updateSort(request);
        return Result.ok();
    }
}
