package com.oroarmor.core.game;

import com.oroarmor.core.Destructor;
import com.oroarmor.util.FixedUpdateThread;

public class Game<T extends GameInfo> {

    private GameRenderer<T> gameGraphics;
    private GameLogic<T> gameLogic;

    private FixedUpdateThread renderThread;
    private FixedUpdateThread logicThread;

    public Game(final GameRenderer<T> gameGraphics, final GameLogic<T> gameLogic) {
        this.gameGraphics = gameGraphics;
        this.gameLogic = gameLogic;

        this.renderThread = new FixedUpdateThread(60) {
            @Override
            public void deinitialize() {
                gameGraphics.deinitialize();
                Destructor.destroyAll();
            }

            @Override
            public void initialize() {
                gameGraphics.initialize();
            }

            @Override
            public void tick() {
                gameGraphics.render(1f / 60f);
            }
        };

        this.logicThread = new FixedUpdateThread(20) {
            @Override
            public void deinitialize() {
                gameLogic.deinitialize();
            }

            @Override
            public void initialize() {
                gameLogic.initialize();
            }

            @Override
            public void tick() {
                gameLogic.tick(0.005f);
            }
        };

    }

    public GameRenderer<T> getGameGraphics() {
        return this.gameGraphics;
    }

    public void setGameGraphics(final GameRenderer<T> gameGraphics) {
        this.gameGraphics = gameGraphics;
    }

    public GameLogic<T> getGameLogic() {
        return this.gameLogic;
    }

    public void setGameLogic(final GameLogic<T> gameLogic) {
        this.gameLogic = gameLogic;
    }

    public FixedUpdateThread getLogicThread() {
        return this.logicThread;
    }

    public void setLogicThread(final FixedUpdateThread logicThread) {
        this.logicThread = logicThread;
    }

    public FixedUpdateThread getRenderThread() {
        return this.renderThread;
    }

    public void setRenderThread(final FixedUpdateThread renderThread) {
        this.renderThread = renderThread;
    }

    public Game<T> run() {
        this.renderThread.start();
        this.logicThread.start();

        try {
            this.renderThread.join();
            this.logicThread.join();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

}
