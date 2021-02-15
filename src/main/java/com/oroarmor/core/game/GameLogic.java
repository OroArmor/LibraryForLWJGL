package com.oroarmor.core.game;

public interface GameLogic<T extends GameInfo> {
    void deinitialize();

    T getGameInfo();

    void setGameInfo(T info);

    void initialize();

    void tick(float updateTime);
}
