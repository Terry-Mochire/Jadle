package dao;

import models.Restaurant;
import models.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oReviewDaoTest {

    private Connection con;

    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    private Sql2oReviewDao reviewDao = new Sql2oReviewDao(sql2o);
    private Sql2oRestaurantDao restaurantDao;

    @Before
    void setUp() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        reviewDao = new Sql2oReviewDao(sql2o);
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        con = sql2o.open();
    }

    @After
    void tearDown() {
        con.close();
    }

    public Review setUpReview(){
        Review review = new Review("Great restaurant", "John Jurasek", 4, 100);
        reviewDao.add(review);
        return review;
    }

    public Restaurant setUpRestaurant(){
        return new Restaurant("Art Cafe", "1234, Nairobi", "+254", "+254712345678", "www.artcafe.com", "artcafe@example.com");
    }

    public Review setupReviewForRestaurant(Restaurant restaurant) {
        Review review = new Review("great", "Kim", 4, restaurant.getId());
        reviewDao.add(review);
        return review;
    }


    @Test
    void add_SetsId() {
           Review testReview = setUpReview();
           reviewDao.add(testReview);
           assertEquals(1, testReview.getId());
    }

    @Test
    void getAllReturnsAllAddedReviews() throws Exception {
        Review testReview = setUpReview();
        Review testReview2 = setUpReview();
        reviewDao.add(testReview);
        reviewDao.add(testReview2);
        assertEquals( 2, reviewDao.getAll().size());
    }

    @Test
    void getAllReviewsByRestaurant() {
        Restaurant testRestaurant = setUpRestaurant();
        Restaurant otherRestaurant = setUpRestaurant(); //add in some extra data to see if it interferes
        Review review1 = setupReviewForRestaurant(testRestaurant);
        Review review2 = setupReviewForRestaurant(testRestaurant);
        Review reviewForOtherRestaurant = setupReviewForRestaurant(otherRestaurant);
        assertEquals(2, reviewDao.getAllReviewsByRestaurant(testRestaurant.getId()).size());
    }

    @Test
    void deleteById() {
        Review testReview = setUpReview();
        Review otherReview = setUpReview();
        reviewDao.add(testReview);
        assertEquals(2, reviewDao.getAll().size());
        reviewDao.deleteById(testReview.getId());
        assertEquals(1, reviewDao.getAll().size());
    }

    @Test
    void clearAll() {
        Review testReview = setUpReview();
        Review otherReview = setUpReview();
        reviewDao.add(testReview);
        reviewDao.add(otherReview);
        reviewDao.clearAll();
        assertEquals(0, reviewDao.getAll().size());
    }
}