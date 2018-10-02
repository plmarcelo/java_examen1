package com.privalia.tool;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExtendedArrayListTest {

    @Test
    public void listGetsReversed() {
        ExtendedList<String> namesList =  new ExtendedArrayList<>();
        namesList.add("John");
        namesList.add("Samuel");
        namesList.add("Tom");

        namesList.reverse();

        assertEquals(namesList.get(0), "Tom");
        assertEquals(namesList.get(1), "Samuel");
        assertEquals(namesList.get(2), "John");
    }
}