package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.model.Restaurant;
import com.github.ivansavelyev.votingsystem.repository.RestaurantRepository;
import com.github.ivansavelyev.votingsystem.util.ValidationUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.checkNotFoundWithId;
import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.getFromOptional;


@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant get(int id) {
        return getFromOptional(restaurantRepository.findById(id), id);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllWithMenusAndDishes() {
        return restaurantRepository.getAllWithMenuAndDishes();
    }

    @Cacheable("restaurants")
    public Restaurant getAllWithMenusAndDishesById(int id) {
        return checkNotFoundWithId(restaurantRepository.getWithMenuAndDishes(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        ValidationUtil.checkNew(restaurant);
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant, int id) {
        Assert.notNull(restaurant, "restaurant must not be null");
        ValidationUtil.assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }
}
