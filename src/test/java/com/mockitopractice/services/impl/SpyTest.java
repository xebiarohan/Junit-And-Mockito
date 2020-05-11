package com.mockitopractice.services.impl;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyTest {

    @Test
    public void test() {
        ArrayList listSpy = spy(ArrayList.class);
        assertEquals(0,listSpy.size());

        listSpy.add("alpha");

        assertEquals(1,listSpy.size());

        when(listSpy.size()).thenReturn(5);
        assertEquals(5,listSpy.size());
    }
}
