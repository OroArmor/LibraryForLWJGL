package com.oroarmor.core.game;

import com.oroarmor.util.FixedUpdateThread;

public class Game<T extends GameInfo> {

	private GameRenderer<T> gameGraphics;
	private GameLogic<T> gameLogic;

	private FixedUpdateThread renderThread;
	private FixedUpdateThread logicThread;

	public Game(GameRenderer<T> gameGraphics, GameLogic<T> gameLogic) {
		this.gameGraphics = gameGraphics;
		this.gameLogic = gameLogic;

		renderThread = new FixedUpdateThread(60) {
			@Override
			public void initalize() {
				gameGraphics.initialize();
			}

			@Override
			public void tick() {
				gameGraphics.render(1f / 60f);
			}

			@Override
			public void deinitalize() {
				gameGraphics.deinitialize();
			}
		};

		logicThread = new FixedUpdateThread(20) {
			@Override
			public void tick() {
				gameLogic.tick(0.05f);
			}

			@Override
			public void initalize() {
				gameLogic.initialize();
			}

			@Override
			public void deinitalize() {
				gameLogic.deinitialize();
			}
		};

	}

	public Game<T> run() {
		renderThread.start();
		logicThread.start();

		try {
			renderThread.join();
			logicThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this;
	}

	public GameRenderer<T> getGameGraphics() {
		return gameGraphics;
	}

	public void setGameGraphics(GameRenderer<T> gameGraphics) {
		this.gameGraphics = gameGraphics;
	}

	public GameLogic<T> getGameLogic() {
		return gameLogic;
	}

	public void setGameLogic(GameLogic<T> gameLogic) {
		this.gameLogic = gameLogic;
	}

	public FixedUpdateThread getRenderThread() {
		return renderThread;
	}

	public void setRenderThread(FixedUpdateThread renderThread) {
		this.renderThread = renderThread;
	}

	public FixedUpdateThread getLogicThread() {
		return logicThread;
	}

	public void setLogicThread(FixedUpdateThread logicThread) {
		this.logicThread = logicThread;
	}

}
