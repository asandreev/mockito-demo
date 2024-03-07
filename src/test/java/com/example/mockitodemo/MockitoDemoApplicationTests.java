package com.example.mockitodemo;

import com.example.mockitodemo.services.Greeter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class MockitoDemoApplicationTests {

    @Spy Greeter greeterSpy;
    @Mock Greeter greeterMock;
    @Autowired Greeter greeterBean;

    Logger logger = LoggerFactory.getLogger(MockitoDemoApplicationTests.class);

    @AfterEach
    void resetMocks() {
        Mockito.reset(greeterSpy, greeterMock);
    }

    @Test
    void testSpyNoChangesLocal() {
        Greeter localSpy = Mockito.spy(greeterBean);
        logger.info(localSpy.greeting("Tester"));
        Mockito.verify(localSpy).greeting("Tester1");
    }

    @Test
    void testSpyNoChanges() {
        logger.info(greeterSpy.greeting("Tester"));
    }

    @Test
    void testSpyMockedNoRealMethodInvocation() {
        Mockito
                .doReturn("Good night, Tester. This is a spy.")
                .when(greeterSpy)
                .greeting(any());

        String message = greeterSpy.greeting("Tester");
        logger.info(message);
    }

    @Test
    void testSpyMockedWithUnexpectedRealMethodInvocation() {
        Mockito
                .when(greeterSpy.greeting(any()))
                .thenReturn("Good night, Tester. This is a spy.");

        String message = greeterSpy.greeting("Tester");
        logger.info(message);
    }

    @Test
    void testMockNoChanges() {
        String message = greeterMock.greeting("Tester");
        logger.info(message);
    }

    @Test
    void testMockMocked() {
        Mockito
                .when(greeterMock.greeting(any()))
                //                .thenCallRealMethod()
                .thenReturn("Good night, Tester. This is a mock.");

        String message = greeterMock.greeting("Tester");

        logger.info(message);
    }

    @Test
    void testMockMockedWithRealMethodInvocation() {
        Mockito
                .when(greeterMock.greeting(any()))
                .thenCallRealMethod()
                .thenReturn("Good night, Tester. This is a mock.");

        String message = greeterMock.greeting("Tester"); //note the NPE here because the logger in Greeter's mock is null (!)

        logger.info(message);
    }

    @Test
    void testOldSchoolMockMockedWithRealMethodInvocation() {

        Greeter greeterLocalMock = Mockito.mock(Greeter.class);

        Mockito
                .when(greeterLocalMock.greeting(any()))
                .thenCallRealMethod();

        String message = greeterLocalMock.greeting("Tester"); //note the NPE here because the logger in Greeter's mock is null (!)

        logger.info(message);
    }


}
