package com.mygdx.game.Screens;

import static com.mygdx.game.Extra.Utils.WORLD_HEIGHT;
import static com.mygdx.game.Extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.GokuAnimation;
import com.mygdx.game.MainGame;

public class ScreenAnimacion extends BaseScreen {

    private Stage stage;
    private World world;
    private Image background;
    private GokuAnimation gokuAnimation;
    private Music musissj;
    private Timer timer;
    private boolean cambiar = false;


    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera ortcamera;


    public ScreenAnimacion(MainGame mainGame) {
        super(mainGame);
        this.world = new World(new Vector2(0, 0), true);
        FitViewport fitViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        this.stage = new Stage(fitViewport);
        musissj = mainGame.assetManager.gettransformacionsonido();

        this.ortcamera = (OrthographicCamera) this.stage.getCamera();
        this.debugRenderer = new Box2DDebugRenderer();

    }

    public void añadirescenario(){
        this.background = new Image(mainGame.assetManager.getescenario());
        this.background.setPosition(0,0);
        this.background.setSize(WORLD_WIDTH,WORLD_HEIGHT);
        this.stage.addActor(this.background);
    }
    //Metodo que se añadir la animacion de goku.
    public void añadiranimacion(){
        //Creamos una estancia de animacion y de le asstemanager con el metodo get sacamos la creacion de la animacion.
        Animation<TextureRegion> gokuanimacion = mainGame.assetManager.getgokuanimation();
        //Creamos una instaciona de GokuAnimation y le pasamos por parametro el mundo, la textura y la posicion por vector.
        this.gokuAnimation = new GokuAnimation(this.world,gokuanimacion,new Vector2(1.5f,0.7f));
        //Añadimos el actor.
        this.stage.addActor(this.gokuAnimation);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();
        this.debugRenderer.render(this.world,this.ortcamera.combined);

        timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                cambiar = true;
            }
        },4f,4f);

        if(cambiar == true){
            mainGame.setScreen(new GameScreen(mainGame));
        }
    }


    @Override
    public void show() {
        añadirescenario();
        añadiranimacion();
        musissj.play();

    }

    @Override
    public void hide() {
        this.gokuAnimation.remove();
        this.gokuAnimation.detach();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();
    }
}
