package dao;

import models.Foodtype;

import java.util.List;

public interface FoodtypeDao {
    //create
    void add(Foodtype foodtype);

    //read
    List<Foodtype> getAll();
    // List<Restaurant> getAllRestaurantsForAFoodtype(int id);

    //update

    //delete
    void deleteById(int id);
    void clearAll();
}
