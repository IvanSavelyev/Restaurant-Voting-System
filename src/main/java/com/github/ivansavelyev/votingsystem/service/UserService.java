package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.AuthUser;
import com.github.ivansavelyev.votingsystem.model.User;
import com.github.ivansavelyev.votingsystem.repository.UserRepository;
import com.github.ivansavelyev.votingsystem.to.UserTo;
import com.github.ivansavelyev.votingsystem.util.UserUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;

import static com.github.ivansavelyev.votingsystem.util.UserUtil.prepareToSave;
import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.*;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return prepareAndSave(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    public User get(int id) {
        return getFromOptional(repository.findById(id), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        prepareAndSave(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }

    @Override
    public AuthUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthUser(user);
    }

    private User prepareAndSave(User user) {
        return repository.save(prepareToSave(user));
    }
}