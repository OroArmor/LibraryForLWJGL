package com.oroarmor.util;

public abstract class FixedUpdateThread extends Thread {

	private long nanosPerTick;

	public FixedUpdateThread(double ticksPerSecond) {
		nanosPerTick = (long) ((1d / ticksPerSecond) * 1_000_000);
	}

	@Override
	public void run() {
		long nanos = System.nanoTime();
		tick();

		while (nanosPerTick - (System.nanoTime() - nanos) > 0) {
		}
	}

	public abstract void tick();

}
