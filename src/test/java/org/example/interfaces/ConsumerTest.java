package org.example.interfaces;

import org.example.interfaces.Consumer;
import org.example.models.Buffer;
import org.example.models.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class ConsumerTest {

    private ConsumerImplementation consumer;
    private Buffer buffer;

    @Before
    public void setUp() {
        // Create a mock of Buffer
        buffer = mock(Buffer.class);
        // Instantiate ConsumerImplementation with the mocked Buffer
        consumer = new ConsumerImplementation(buffer);
    }

    @Test
    @DisplayName("Consumer should be running initially")
    public void testIsRunningInitiallyTrue() {
        // Assert that the consumer is initially running
        assertTrue("Consumer should be initially running", consumer.isRunning());
    }

    @Test
    @DisplayName("Consumer should stop running after calling stopRunning")
    public void testIsRunningAfterStopRunning() {
        // Call stopRunning and assert the change in running state
        consumer.stopRunning();
        assertFalse("Consumer should not be running after stopRunning is called", consumer.isRunning());
    }

    @Test
    @DisplayName("Consumer's run method should add an item to the buffer")
    public void testRunAddsItemToBuffer() {
        // Call run method and verify if an item is added to the buffer
        consumer.run();
        verify(buffer, times(1)).add(any(Item.class));
    }


    private class ConsumerImplementation implements Consumer {
        private final Buffer buffer;
        private volatile boolean running = true;

        ConsumerImplementation(Buffer buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            Item item = new Item("ProducedItem");
            buffer.add(item);
        }

        @Override
        public void stopRunning() {
            running = false;
        }

        public boolean isRunning() {
            return running;
        }
    }
}