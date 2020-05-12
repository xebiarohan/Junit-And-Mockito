package com.powermockpractice;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
class SystemUnderTestTest {

    @Test
    public void test() {

        Dependency dependency = mock(Dependency.class);

        SystemUnderTest systemUnderTest = new SystemUnderTest();
        List<Integer> stats = Arrays.asList(1,2,3);

        when(dependency.retrieveAllStats()).thenReturn(stats);

        PowerMockito.mockStatic(UtilityClass.class);

        when(UtilityClass.staticMethod(6)).thenReturn(100);

        int result = systemUnderTest.methodCallingAStaticMethod();

        assertEquals(100,result);

        PowerMockito.verifyStatic(UtilityClass.class);
        UtilityClass.staticMethod(6);
    }

}