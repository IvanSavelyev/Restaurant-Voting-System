package ru.graduation.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.graduation.model.Dish;
import ru.graduation.model.Menu;
import ru.graduation.repository.DishRepository;
import ru.graduation.repository.MenuRepository;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.util.ValidationUtil;
import ru.graduation.util.exception.NotFoundException;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class MenuService {

    private final DishRepository dishRepository;

    private final MenuRepository menuRepository;

    private final RestaurantRepository restaurantRepository;

    public Menu get(int id){
        return checkNotFoundWithId(menuRepository.findById(id).get(), id);
    }

    public Menu get(int id, int restaurantId){
        return checkNotFoundWithId(menuRepository.findMenuByIdAndRestaurantId(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(dishRepository.deleteByIdAndMenuId(id, restaurantId) != 0 , id);
    }

//    public void delete(int id) throws NotFoundException {
//        checkNotFoundWithId(dishRepository.deleteById(id) != 0 , id);
//    }

    public Menu create(Menu menu, int restaurantId) {
        ValidationUtil.checkNew(menu);
        if (!menu.isNew() && get(menu.id(), restaurantId) == null) {
            return null;
        }
        menu.setRestaurant(restaurantRepository.getById(restaurantId));
        return menuRepository.save(menu);
    }


//    public Dish create(Dish dish, int menuId, int restaurantId) {
//        ValidationUtil.checkNew(dish);
//        if (!dish.isNew() && get(dish.id(), restaurantId) == null) {
//            return null;
//        }
//        Menu menu = menuRepository.findMenuByIdAndRestaurantId(menuId, restaurantId);
//        if(menu == null){
//            Menu createdMenu = new Menu();
//            createdMenu.setRestaurant(restaurantRepository.getById(restaurantId));
//        }
//
//        dish.setMenu(menuRepository.getById(menuId));
//        return dishRepository.save(dish);
//    }
}
