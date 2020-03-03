package com.oroarmor.util;

public abstract class FixedUpdateThread extends Thread {

	private long nanosPerTick;
	private boolean active = true;

	public FixedUpdateThread(double ticksPerSecond) {
		nanosPerTick = (long) ((1d / ticksPerSecond) * 1_000_000);
	}

	@Override
	public void run() {
		initalize();
		while (active) {
			long nanos = System.nanoTime();
			tick();

			while (nanosPerTick - (System.nanoTime() - nanos) > 0) {
			}
		}
		deinitalize();
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public abstract void tick();

	public abstract void initalize();

	public abstract void deinitalize();

}
