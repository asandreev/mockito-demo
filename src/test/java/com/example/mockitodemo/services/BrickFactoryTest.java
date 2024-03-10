package com.example.mockitodemo.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class BrickFactoryTest {

    @BeforeAll
    public static void setUp() {
        //We inject a spy of the production factory in the abstract brick factory
        // We are now going to get this spy when asking for a factory.
        // We can now easily instrument it to produce mocks.
        BrickFactory testBrickFactory = Mockito.spy(new BrickFactory());
        BrickFactory.setFactoryInstance(testBrickFactory);
    }

    @AfterEach
    public void resetMocks() {
        //We need to reset the mocks after each test
        // so the tests remain independent.
        // We don't want changes in one test method
        // to impact the next ones. Also, the order of
        // running the tests shouldn't matter.
        // If we use common mocking for all test methods,
        // then we would do it in @BeforeEach method.
        Mockito.reset(BrickFactory.getFactory());
    }

    @Test
    void testBlueBrick() {

        //We create a mock "Blue Brick" that we need for test purposes.
        Brick blueBrick = Mockito.mock(Brick.class);
        Mockito.when(blueBrick.getColor()).thenReturn("Blue");

        //We instruct our factory to produce the blue bricks
        Mockito.doReturn(blueBrick).when(BrickFactory.getFactory()).getBrick();

        Brick producedBrick = BrickFactory.getFactory().getBrick();

        assertEquals("Blue", producedBrick.getColor());
    }

    @Test
    void testNormalBrick() {
        // If we don't instrument the factory then we get production bricks
        Brick producedBrick = BrickFactory.getFactory().getBrick();

        assertEquals("Red", producedBrick.getColor());
    }
}