package com.oroarmor.core.game;

public interface GameLogic {
	public void initialize();

	public void tick(float updateTime);

	public void deinitialize();

	public GameInfo getGameInfo();

	public void setGameInfo(GameInfo info);
}
