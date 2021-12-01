package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.util.exception.NotFoundException;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Restaurant get(Integer id) {
        return restaurantRepository.findById(id).get();
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(restaurantRepository.delete(id) != 0, id);
    }

    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        restaurantRepository.save(restaurant);
    }
}
