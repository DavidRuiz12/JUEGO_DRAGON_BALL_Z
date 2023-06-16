package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Extra.AssetMan;
import com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.GameStart;
import com.mygdx.game.Screens.ScreenAnimacion;

//Clae Maingame que extiende de Game
public class MainGame extends Game {

	public AssetMan assetManager;
	private GameStart gamestart;
	public GameOverScreen gameOverScreen;


	@Override
	public void create() {
		this.assetManager = new AssetMan();
		this.gamestart = new GameStart(this);
		this.gameOverScreen = new GameOverScreen(this);
		setScreen(this.gamestart);
	}
}
