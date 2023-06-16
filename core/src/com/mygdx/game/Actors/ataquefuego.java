package com.mygdx.game.Actors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MainGame;
import com.mygdx.game.Screens.GameScreen;

public class ataquefuego extends Actor implements InputProcessor {

    //Creamos las variables para el tamaño del personaje.
    public static final float GOKU_WIDTH = 1f;
    private static final float GOKU_HEIGHT = 1f;

    //Creacion de variables de TextureRegion,el Body,la Fixture y el mundo.
    private TextureRegion gokuTR;
    public Body bodygoku;
    public Fixture fixturegoku;
    private World world;
    private Vector2 position;
    private Sound ataquesound;
    private int x = 0;
    private int y = 0;
    public GameScreen games;
    public MainGame mainGame;
    public boolean cambiopossder = false;
    public boolean cambiopossizq = false;


    //Creacion de constructor al que le pasamos el mundo la TextureRegion y el vector.
    public ataquefuego(World world, TextureRegion trgoku, Vector2 position, Sound sound){
        this.world = world;
        this.gokuTR = trgoku;
        this.position = position;
        this.ataquesound = sound;

        //Instaciamos los metodos para la creacion del cuerpo y la fixture de ataquefuego.
        createBodyGoku(position);
        createFixtureGoku();
    }

    //Creacion del metodo para la creacion del Body de ataquefuego.
    private void createBodyGoku(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        bodygoku = world.createBody(def);

    }

    //Creacion del metodo para la creacion de la Fixture de ataquefuego.
    private void createFixtureGoku() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.2f,0.2f);
        this.fixturegoku = bodygoku.createFixture(shape,8);
        shape.dispose();
    }



    public boolean isOutOfScreen(){
        return this.bodygoku.getPosition().x >= 5f;
    }
    public boolean isOutOfScreennegative(){
        return this.bodygoku.getPosition().x <= -1f;
    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    //Sobreescribimos el metodo draw para dibujar a ataquefuego en la pantalla.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodygoku.getPosition().x - (GOKU_WIDTH/2), this.bodygoku.getPosition().y - (GOKU_HEIGHT/2) );
        batch.draw(this.gokuTR, getX(),getY(),GOKU_WIDTH,GOKU_HEIGHT);
    }

    public void detach(){
        bodygoku.destroyFixture(fixturegoku);
        world.destroyBody(bodygoku);
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //Este metodo se encarga de guardar las coordernada donde estamos tocando en la pantalla.
        x = screenX;
    //Si la coordenada horizontal donde hemos pulsado en la pantalla es mas de 500, el sprite de ataquefuego mira hacia la derecha.
            if ( x >= 500){
                ataquesound.play();
                bodygoku.setLinearVelocity(5,0);
                cambiopossder = true;
    //Si es menos de 500 mira hacia la izquierda.
            }else{
                ataquesound.play();
                bodygoku.setLinearVelocity(-5,0);
                cambiopossizq = true;

            }

       return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
