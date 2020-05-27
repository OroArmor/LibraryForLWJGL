package com.oroarmor.util;

/**
 * A {@link Thread} that runs a specific number of times per second
 *
 * @author Eli Orona
 *
 */
public abstract class FixedUpdateThread extends Thread {

	/**
	 * The time in nanos that each loop of the thread runs
	 */
	private final long nanosPerTick;

	/**
	 * Is the thread active
	 */
	private boolean active = true;

	/**
	 * Creates a new {@link FixedUpdateThread}
	 *
	 * @param ticksPerSecond The maximum number of times to run the thread per
	 *                       second
	 */
	public FixedUpdateThread(final double ticksPerSecond) {
		this.nanosPerTick = (long) (1d / ticksPerSecond * 1_000_000);
	}

	/**
	 * Runs once when the thread exits
	 */
	public abstract void deinitalize();

	/**
	 * Runs once when the thread is run
	 */
	public abstract void initalize();

	@Override
	public void run() {
		this.initalize();
		while (this.active) {
			final long nanos = System.nanoTime();
			this.tick();

			while (this.nanosPerTick - (System.nanoTime() - nanos) > 0) {
			}
		}
		this.deinitalize();
	}

	/**
	 *
	 * @param active Sets the activeness of the thread
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * Runs each execution of the thread
	 */
	public abstract void tick();

}
