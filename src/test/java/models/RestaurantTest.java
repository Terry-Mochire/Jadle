package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    public Restaurant setUpRestaurant(){
        return new Restaurant("Art Cafe", "1234, Nairobi", "+254", "+254712345678", "www.artcafe.com", "artcafe@example.com");
    }

    @Test
    void newObjectInstantiatesCorrectly(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals(true, testRestaurant instanceof Restaurant);
    }

    @Test
    void getName_returnCorrectName(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals("Art Cafe", testRestaurant.getName());
    }


    @Test
    void getAddress_returnCorrectAddress(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals("1234, Nairobi", testRestaurant.getAddress());
    }

    @Test
    void getZipCode_returnCorrectZipCode(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals("+254", testRestaurant.getZipcode());
    }

    @Test
    void getPhone_returnCorrectPhone(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals("+254712345678", testRestaurant.getPhone());
    }

    @Test
    void getWebsite_returnCorrectWebsite(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals("www.artcafe.com", testRestaurant.getWebsite());
    }

    @Test
    void getEmail_returnCorrectEmail(){
        Restaurant testRestaurant = setUpRestaurant();
        assertEquals("artcafe@example.com", testRestaurant.getEmail());
    }

    @Test
    public void setNameSetsCorrectName() throws Exception {
        Restaurant testRestaurant = setUpRestaurant();
        testRestaurant.setName("Steak House");
        assertNotEquals("Fish Witch",testRestaurant.getName());
    }

    @Test
    public void setAddressSetsCorrectAddress() throws Exception {
        Restaurant testRestaurant = setUpRestaurant();
        testRestaurant.setAddress("6600 NE Ainsworth");
        assertNotEquals("214 NE Broadway", testRestaurant.getAddress());
    }

    @Test
    public void setZipSetsCorrectZip() throws Exception {
        Restaurant testRestaurant = setUpRestaurant();
        testRestaurant.setZipcode("78902");
        assertNotEquals("97232", testRestaurant.getZipcode());
    }
    @Test
    public void setPhoneSetsCorrectPhone() throws Exception {
        Restaurant testRestaurant = setUpRestaurant();
        testRestaurant.setPhone("971-898-7878");
        assertNotEquals("503-402-9874", testRestaurant.getPhone());
    }

    @Test
    public void setWebsiteSetsCorrectWebsite() throws Exception {
        Restaurant testRestaurant = setUpRestaurant();
        testRestaurant.setWebsite("http://steakhouse.com");
        assertNotEquals("http://fishwitch.com", testRestaurant.getWebsite());
    }

    @Test
    public void setEmailSetsCorrectEmail() throws Exception {
        Restaurant testRestaurant = setUpRestaurant();
        testRestaurant.setEmail("steak@steakhouse.com");
        assertNotEquals("hellofishy@fishwitch.com", testRestaurant.getEmail());
    }


}