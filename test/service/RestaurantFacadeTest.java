/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Restaurant;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Abed
 */
public class RestaurantFacadeTest {
    
    public RestaurantFacadeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of create method, of class RestaurantFacade.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Restaurant entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        instance.create(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class RestaurantFacade.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Restaurant entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        instance.edit(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class RestaurantFacade.
     */
    @Test
    public void testRemove() throws Exception {
        System.out.println("remove");
        Restaurant entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        instance.remove(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class RestaurantFacade.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Object id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        Restaurant expResult = null;
        Restaurant result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class RestaurantFacade.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        List<Restaurant> expResult = null;
        List<Restaurant> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class RestaurantFacade.
     */
    @Test
    public void testFindRange() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        List<Restaurant> expResult = null;
        List<Restaurant> result = instance.findRange(range);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class RestaurantFacade.
     */
    @Test
    public void testCount() throws Exception {
        System.out.println("count");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByCuisine method, of class RestaurantFacade.
     */
    @Test
    public void testFindByCuisine() throws Exception {
        System.out.println("findByCuisine");
        Long id = 4L;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        RestaurantFacade instance = (RestaurantFacade)container.getContext().lookup("java:global/classes/RestaurantFacade");
        List<Restaurant> expResult;
        List<Restaurant> result = instance.findByCuisine(id);
       // assertEquals(expResult, result);
        System.out.println(result);
        container.close();
      
    }
    
}
