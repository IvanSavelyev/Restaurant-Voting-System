package com.github.ivansavelyev.votingsystem.service;

import com.github.ivansavelyev.votingsystem.model.Menu;
import com.github.ivansavelyev.votingsystem.repository.MenuRepository;
import com.github.ivansavelyev.votingsystem.repository.RestaurantRepository;
import com.github.ivansavelyev.votingsystem.to.MenuTo;
import com.github.ivansavelyev.votingsystem.util.MenuUtil;
import com.github.ivansavelyev.votingsystem.web.exeption.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.checkNotFoundWithId;
import static com.github.ivansavelyev.votingsystem.util.ValidationUtil.getFromOptional;


@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public Menu get(int id) {
        return getFromOptional(menuRepository.findById(id), id);
    }

    public Menu getByDateAndRestaurant(LocalDate localDate, int restaurantId) {
        return menuRepository.getMenuByDateAndRestaurantId(localDate, restaurantId);
    }

    public Menu create(MenuTo menuTo) {
        Menu menu = checkAndGet(menuTo);
        Integer restaurantId = menuTo.getRestaurantId();
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuRepository.save(menu);
    }

    public void update(MenuTo menuTo, int id) {
        Menu menu = checkAndGet(menuTo);
        Integer restaurantId = menuTo.getRestaurantId();
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        menuRepository.save(menu);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(menuRepository.delete(id) != 0, id);
    }

    private Menu checkAndGet(MenuTo menuTo) {
        Assert.notNull(menuTo, "menuTo must not be null");
        return MenuUtil.createFromTo(menuTo);
    }
}
