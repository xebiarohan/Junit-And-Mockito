package com.mockitopractice.services.impl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatcherTest {

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1,2,3,4);
        // List
        assertThat(list,hasSize(4));

        assertThat(list,hasItems(1,2,3,4));

        assertThat(list,everyItem(greaterThan(0)));
        assertThat(list,everyItem(lessThan(10)));


        // String
     ///   assertThat("", isEmptyString());
     //   assertThat(null,isEmptyOrNullString());

        //Array
        Integer[] array = {1,2,3};
        assertThat(array,arrayWithSize(3));

        assertThat(array,arrayContaining(1,2,3));

        assertThat(array,arrayContainingInAnyOrder(1,3,2));
    }
}
