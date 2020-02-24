package com.oroarmor.core.game;

public interface GameRenderer {
	public void initialize();

	public void render(float renderTime);

	public void deinitialize();

	public GameInfo getGameInfo();

	public void setGameInfo(GameInfo info);
}
