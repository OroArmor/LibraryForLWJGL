package com.oroarmor.core.game;

import com.oroarmor.util.FixedUpdateThread;

public class Game {

	private GameRenderer gameGraphics;
	private GameLogic gameLogic;

	private Thread renderThread;
	private Thread logicThread;

	public Game(GameRenderer gameGraphics, GameLogic gameLogic) {
		this.gameGraphics = gameGraphics;
		this.gameLogic = gameLogic;

		renderThread = new FixedUpdateThread(60) {
			@Override
			public void tick() {
				gameGraphics.render(1f / 60f);
			}
		};

		logicThread = new FixedUpdateThread(20) {
			@Override
			public void tick() {
				gameLogic.tick(0.05f);
			}
		};

		renderThread.run();
		logicThread.run();
	}

	public GameRenderer getGameGraphics() {
		return gameGraphics;
	}

	public void setGameGraphics(GameRenderer gameGraphics) {
		this.gameGraphics = gameGraphics;
	}

	public GameLogic getGameLogic() {
		return gameLogic;
	}

	public void setGameLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
	}

	public Thread getRenderThread() {
		return renderThread;
	}

	public void setRenderThread(Thread renderThread) {
		this.renderThread = renderThread;
	}

	public Thread getLogicThread() {
		return logicThread;
	}

	public void setLogicThread(Thread logicThread) {
		this.logicThread = logicThread;
	}

}
