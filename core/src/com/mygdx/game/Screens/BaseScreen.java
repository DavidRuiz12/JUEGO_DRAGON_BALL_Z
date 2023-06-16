package com.mygdx.game.Screens;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MainGame;

//Clase BaseScreen donde van a estar todos los metodos de Screen Implementados en esta que utilizaremos mas tarde.
public class BaseScreen implements Screen {

    protected MainGame mainGame;

    public BaseScreen(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
