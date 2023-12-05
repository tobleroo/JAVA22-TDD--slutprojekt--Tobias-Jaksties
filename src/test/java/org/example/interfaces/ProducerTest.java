package org.example.interfaces;

import org.example.interfaces.Producer;
import org.example.models.Buffer;
import org.example.models.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProducerTest {

    private ProducerImplementation producer;
    private Buffer buffer;

    @Before
    public void setUp() {
        buffer = mock(Buffer.class);
        producer = new ProducerImplementation(buffer);
    }

    @Test
    @DisplayName("Producer should be running initially")
    public void testIsRunningInitiallyTrue() {
        assertTrue("Producer should be initially running", producer.isRunning());
    }

    @Test
    @DisplayName("Producer should stop running after calling stopRunning")
    public void testIsRunningAfterStopRunning() {
        producer.stopRunning();
        assertFalse("Producer should not be running after stopRunning is called", producer.isRunning());
    }

    @Test
    @DisplayName("Producer's run method should add an item to the buffer")
    public void testRunAddsItemToBuffer() {
        producer.run();
        verify(buffer, times(1)).add(any(Item.class));
    }

    private class ProducerImplementation implements Producer {
        private final Buffer buffer;
        private volatile boolean running = true;

        ProducerImplementation(Buffer buffer) {
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
