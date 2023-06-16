package com.mygdx.game.Screens;

import static com.mygdx.game.Extra.Utils.WORLD_HEIGHT;
import static com.mygdx.game.Extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MainGame;

public class GameStart extends BaseScreen{

    private Stage stage;
    private World world;
    private Image image;
    private Music musicastart;
    private Image imagenboton;

    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera ortcamera;

    public GameStart(MainGame mainGame) {
        super(mainGame);

        this.world = new World(new Vector2(0, 0), true);
        FitViewport fitViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        this.stage = new Stage(fitViewport);
        this.musicastart = this.mainGame.assetManager.getMusicStart();

        this.ortcamera = (OrthographicCamera) this.stage.getCamera();
        this.debugRenderer = new Box2DDebugRenderer();
    }

    public void añadirboton(){
        this.imagenboton = new Image(mainGame.assetManager.getbotonstart());
        this.imagenboton.setPosition(1.3f,1f);
        this.imagenboton.setSize(2f,1f);
        this.stage.addActor(this.imagenboton);

    }

    public void añadirescenario(){
        this.image = new Image(mainGame.assetManager.getstart());
        this.image.setPosition(0,0);
        this.image.setSize(WORLD_WIDTH,WORLD_HEIGHT);
        this.stage.addActor(this.image);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();
        this.debugRenderer.render(this.world,this.ortcamera.combined);

        if(Gdx.input.justTouched()){
            mainGame.setScreen(new ScreenAnimacion(mainGame));
            musicastart.stop();
        }

    }

    @Override
    public void show() {
        añadirescenario();
        añadirboton();
        this.musicastart.setLooping(true);
        this.musicastart.play();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
    }
}
