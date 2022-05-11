package dao;

import models.Review;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oReviewDao implements ReviewDao{
    private final Sql2o sql2o;
    public Sql2oReviewDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }


    @Override
    public void add(Review review) {
        String sql = "INSERT INTO reviews(writtenby, content, rating, restaurantid) VALUES (:writtenby, :content, :rating, :restaurantid)";
        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(review)
                    .addParameter("writtenby", review.getWrittenBy())
                    .addParameter("content", review.getContent())
                    .addParameter("rating", review.getRating())
                    .addParameter("restaurantid", review.getRestaurantId())
                    .executeUpdate()
                    .getKey();
            review.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Review> getAll() {
       String sql = "SELECT id FROM reviews";
       try(Connection con = sql2o.open()){
           return con.createQuery(sql)
                   .executeAndFetch(Review.class);
       }
    }

    @Override
    public List<Review> getAllReviewsByRestaurant(int restaurantid) {
        String sql = "SELECT * FROM reviews WHERE restaurantid=:restaurantid";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).
                    addParameter("restaurantid", restaurantid)
                    .executeAndFetch(Review.class);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from reviews WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from reviews";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
