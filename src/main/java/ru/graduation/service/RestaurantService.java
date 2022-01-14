package ru.graduation.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.web.exeption.NotFoundException;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service("RestaurantService")
@Slf4j
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public Restaurant get(int id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found with " + id));
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    @Cacheable("restaurants")
    @Transactional(readOnly = true)
    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurantRepository.save(restaurant);
    }
}
