package com.oroarmor.core.game;

public interface GameRenderer<T extends GameInfo> {
    void deinitialize();

    T getGameInfo();

    void setGameInfo(T info);

    void initialize();

    void render(float renderTime);
}
