package com.automata.threading;

import java.util.concurrent.BlockingQueue;

/**
 * A thread pooled in a ThreadPool
 * 
 * @author Carson Clarke-Magrab
 *
 */
public class PooledThread extends Thread {
	
	private BlockingQueue<Thread> taskQueue;
	private boolean stopped = false;
	
	/**
	 * Constructs a pooled thread around a ThreadPools task queue
	 * 
	 * @param queue the ThreadPool's taskQueue
	 */
	public PooledThread(BlockingQueue<Thread> queue) {
		taskQueue = queue;
	}
	
	/**
	 * Runs the first thread in the task queue
	 */
	public void run() {
		while (!stopped()) {
			try {
				Runnable runner = taskQueue.take();
				runner.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Kills the thread
	 */
	public synchronized void kill() {
		stopped = true;
		interrupt();
	}
	
	/**
	 * Returns if the thread is stopped
	 * 
	 * @return If the thread is stopped
	 */
	public synchronized boolean stopped() {
		return stopped;
	}
}
