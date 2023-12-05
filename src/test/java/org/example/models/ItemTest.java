package org.example.models;

import org.example.models.Item;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    @DisplayName("Constructor should set ID in lowercase")
    public void testItemConstructor() {
        String testId = "TestID";
        Item item = new Item(testId);
        assertEquals(testId.toLowerCase(), item.toString());
    }

    @Test
    @DisplayName("setId should update ID in lowercase")
    public void testSetId() {
        String initialId = "InitialID";
        String newId = "NewID";
        Item item = new Item(initialId);
        item.setId(newId);
        assertEquals(newId.toLowerCase(), item.toString());
    }

    @Test
    @DisplayName("toString should return the ID in lowercase")
    public void testToString() {
        String testId = "TestID";
        Item item = new Item(testId);
        assertEquals(testId.toLowerCase(), item.toString());
    }
}
