package com.mockitopractice.services.impl;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void listSizeTest() {
        List list = mock(List.class);
        when(list.size()).thenReturn(2);
        assertEquals(2,list.size());
    }

    @Test
    public void multipleReturnValue() {
        List list = mock(List.class);
        when(list.size()).thenReturn(2).thenReturn(3);
        assertEquals(2,list.size());
        assertEquals(3,list.size());
    }

    @Test
    public void listGetTest() {
        List list = mock(List.class);
        //Argument Matcher
        when(list.get(anyInt())).thenReturn("Alpha");
        assertEquals("Alpha",list.get(2));
        assertEquals("Alpha",list.get(10));
    }

    @Test
    public void listGetThrowExceptionTest() {
        List list = mock(List.class);
        when(list.get(anyInt())).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class,() -> {
            list.get(0);
        });
    }
}
