package dao;

import models.Review;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oRestaurantDao implements ReviewDao{
    private final Sql2o sql2o;
    public Sql2oRestaurantDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Review review) {

    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public List<Review> getAllReviewsByRestaurant(int restaurantid) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAll() {

    }
}
