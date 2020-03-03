package com.oroarmor.core.game;

public interface GameLogic<T extends GameInfo> {
	public void initialize();

	public void tick(float updateTime);

	public void deinitialize();

	public T getGameInfo();

	public void setGameInfo(T info);
}
