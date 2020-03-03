package com.oroarmor.core.game;

public interface GameRenderer<T extends GameInfo> {
	public void initialize();

	public void render(float renderTime);

	public void deinitialize();

	public T getGameInfo();

	public void setGameInfo(T info);
}
