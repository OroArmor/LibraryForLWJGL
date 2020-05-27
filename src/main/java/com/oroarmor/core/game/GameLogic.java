package com.oroarmor.core.game;

public interface GameLogic<T extends GameInfo> {
	public void deinitialize();

	public T getGameInfo();

	public void initialize();

	public void setGameInfo(T info);

	public void tick(float updateTime);
}
