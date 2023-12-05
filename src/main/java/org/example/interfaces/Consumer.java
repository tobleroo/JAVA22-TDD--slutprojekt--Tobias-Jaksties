package org.example.interfaces;

/**
 * Needs buffer to put Item in. 
 * run starts Consumer
 * stopRunning stops Consumer
 */
public interface Consumer {

	public void run();
	public void stopRunning();
}
