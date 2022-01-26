package com.github.ivansavelyev.votingsystem.web.controllers.user;


import com.github.ivansavelyev.votingsystem.model.User;
import com.github.ivansavelyev.votingsystem.util.SecurityUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping(AccountController.URL)
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Account Controller")
public class AccountController extends AbstractUserController {

    static final String URL = "/api/account";

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return super.get(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        log.info("delete {}", SecurityUtil.authUser());
        userService.delete(SecurityUtil.authId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user) {
        log.info("create {}", user);
        User created = super.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user) {
        log.info("update {} to {}", SecurityUtil.authUser(), user);
        super.update(user);
    }
}