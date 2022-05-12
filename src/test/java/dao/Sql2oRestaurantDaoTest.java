package dao;

import models.Foodtype;
import models.Restaurant;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oRestaurantDaoTest {

    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    private Connection conn;
    private Sql2oRestaurantDao restaurantDao = new Sql2oRestaurantDao(sql2o);
    private Sql2oFoodtypeDao foodtypeDao = new Sql2oFoodtypeDao(sql2o);
    private Sql2oReviewDao reviewDao =  new Sql2oReviewDao(sql2o);


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        restaurantDao = new Sql2oRestaurantDao(sql2o);
        foodtypeDao = new Sql2oFoodtypeDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingFoodSetsId() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertNotEquals(0, testRestaurant.getId());
    }

    @Test
    public void addedRestaurantsAreReturnedFromGetAll() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        assertEquals(1, restaurantDao.getAll().size());
    }

    @Test
    public void noRestaurantsReturnsEmptyList() throws Exception {
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectRestaurant() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        Restaurant otherRestaurant = setupRestaurant();
        assertEquals(testRestaurant, restaurantDao.findById(testRestaurant.getId()));
    }

    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.update(testRestaurant.getId(), "a", "b", "c", "d", "e", "f");
        Restaurant foundRestaurant = restaurantDao.findById(testRestaurant.getId());
        assertEquals("a", foundRestaurant.getName());
        assertEquals("b", foundRestaurant.getAddress());
        assertEquals("c", foundRestaurant.getZipcode());
        assertEquals("d", foundRestaurant.getPhone());
        assertEquals("e", foundRestaurant.getWebsite());
        assertEquals("f", foundRestaurant.getEmail());
    }

    @Test
    public void deleteByIdDeletesCorrectRestaurant() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        Restaurant otherRestaurant = setupRestaurant();
        restaurantDao.deleteById(testRestaurant.getId());
        assertEquals(1, restaurantDao.getAll().size());
    }

    @Test
    public void clearAll() throws Exception {
        Restaurant testRestaurant = setupRestaurant();
        Restaurant otherRestaurant = setupRestaurant();
        restaurantDao.clearAll();
        assertEquals(0, restaurantDao.getAll().size());
    }

    @Test
    public void getAllFoodtypesByRestaurant_returnsFoodTypesCorrectly() throws Exception {
        Foodtype testFoodType = new Foodtype("Seafood");
        Foodtype otherFoodType = new Foodtype("BarFood");
        foodtypeDao.add(testFoodType);
        foodtypeDao.add(otherFoodType);

        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);
        restaurantDao.addRestaurantToFoodType(testRestaurant, testFoodType);
        restaurantDao.addRestaurantToFoodType(testRestaurant, otherFoodType);

        Foodtype[] foodtypes = {testFoodType, otherFoodType};

        assertEquals(Arrays.asList(foodtypes), restaurantDao.getAllFoodtypesByRestaurant(testRestaurant.getId()));
    }


    @Test
    public void deletingRestaurantsAlsoUpdatesJoinTable() throws Exception {
        Foodtype testFoodType = new Foodtype("Seafood");
        foodtypeDao.add(testFoodType);

        Restaurant testRestaurant = setupRestaurant();
        restaurantDao.add(testRestaurant);

        Restaurant altRestaurant = setupAltRestaurant();
        restaurantDao.add(altRestaurant);

        restaurantDao.addRestaurantToFoodType(testRestaurant, testFoodType);
        restaurantDao.addRestaurantToFoodType(altRestaurant, testFoodType);

        restaurantDao.deleteById(testRestaurant.getId());
        assertEquals(0, restaurantDao.getAllFoodtypesByRestaurant(testRestaurant.getId()).size());
    }

    //helpers

    public Restaurant setupRestaurant (){
        Restaurant restaurant = new Restaurant("Fish Omena", "214 NE Ngara", "97232", "254-402-9874", "http://fishwitch.com", "hellofishy@fishwitch.com");
        restaurantDao.add(restaurant);
        return restaurant;
    }

    public Restaurant setupAltRestaurant (){
        Restaurant restaurant = new Restaurant("Cali Roll Sushi", "123 SW Kiambu", "12345", "254-402-9874");
        restaurantDao.add(restaurant);
        return restaurant;
    }
}