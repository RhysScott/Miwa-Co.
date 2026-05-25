package org.miwa.miwa.controller.pub;

import lombok.RequiredArgsConstructor;
import org.miwa.miwa.dto.Result;
import org.miwa.miwa.entity.Person;
import org.miwa.miwa.service.PeopleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping("/people")
    public Result<List<Person>> list() {
        return Result.ok(peopleService.list());
    }

    @GetMapping("/people/{id}")
    public Result<Person> detail(@PathVariable Long id) {
        return Result.ok(peopleService.getById(id));
    }
}
