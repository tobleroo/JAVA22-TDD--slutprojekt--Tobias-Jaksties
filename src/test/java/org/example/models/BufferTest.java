package org.example.models;

import org.example.models.Buffer;
import org.example.models.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class BufferTest {

    private Buffer buffer;

    @Before
    public void setUp() {
        buffer = new Buffer();
    }

    @Test
    @DisplayName("Adding an item to the buffer should succeed")
    public void testAddItem() {
        Item item = new Item("test");
        assertTrue("Item should be added", buffer.add(item));
    }

    @Test
    @DisplayName("Removing an item should return the last added item")
    public void testRemoveItem() {
        Item item = new Item("test");
        buffer.add(item);
        assertEquals("The removed item should be the one that was added", item, buffer.remove());
    }

    @Test
    @DisplayName("Removing from an empty buffer should handle InterruptedException")
    public void testRemoveFromEmptyBufferAndInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            // Attempt to remove an item from the empty buffer
            buffer.remove();
        });

        thread.start();
        Thread.sleep(100);

        thread.interrupt();

        thread.join(1000);

    }

}
