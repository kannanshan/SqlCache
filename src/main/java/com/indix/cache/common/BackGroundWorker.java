/**
 * @packageName : com.indix.cache.common
 * @className : BackGroundWorker.java
 * @date : 29-May-2018
 * @author : kannans
 * @version : 1.0
 */

package com.indix.cache.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BackGroundWorker implements Runnable {

	private long timeOut;
	private boolean isRunning = true;
	private Runnable work;
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 500, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue());
	private static List<BackGroundWorker> workerList = new ArrayList();

	public BackGroundWorker(long timeOut, Runnable work) {
		this.timeOut = timeOut;
		this.work = work;
	}

	@Override
	public void run() {
		while (isRunning) {
			try {
				this.work.run();
				Thread.sleep(this.timeOut);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @return the timeOut
	 */
	public long getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut
	 *            the timeOut to set
	 */
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}

	/**
	 * @return the isRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * @param isRunning
	 *            the isRunning to set
	 */
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public static void initiateWorker(long timeOut, Runnable work) {
		BackGroundWorker worker = new BackGroundWorker(timeOut, work);
		workerList.add(worker);
		executor.submit(worker);

	}

	public static void stopWorkers() {
		for (BackGroundWorker worker : workerList) {
			worker.setRunning(false);
		}
	}

	public static void submitWork(Runnable work) {
		executor.submit(work);
	}

}
