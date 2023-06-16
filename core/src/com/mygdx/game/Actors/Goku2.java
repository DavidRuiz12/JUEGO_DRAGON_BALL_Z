package com.mygdx.game.Actors;

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

public class Goku2 extends Actor {
    //Creamos las variables para el tamaño del personaje.
    public static final float GOKU2_WIDTH = 1f;
    private static final float GOKU2_HEIGHT = 1f;

    //Creacion de variables de TextureRegion,el Body,la Fixture y el mundo.
    private TextureRegion gokuTR;
    public Body bodygoku2;
    public Fixture fixturegoku2;
    private World world;
    private Vector2 position;



    //Creacion de constructor al que le pasamos el mundo la TextureRegion y el vector.
    public Goku2 (World world,TextureRegion trgoku,Vector2 position){
        this.world = world;
        this.gokuTR = trgoku;
        this.position = position;


        //Instaciamos los metodos para la creacion del cuerpo y la fixture de ataquefuego.
        createBodyGoku2(position);
    }

    //Creacion del metodo para la creacion del Body de ataquefuego.
    private void createBodyGoku2(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        bodygoku2 = world.createBody(def);

    }


    @Override
    public void act(float delta) {
        super.act(delta);
    }

    //Sobreescribimos el metodo draw para dibujar a ataquefuego en la pantalla.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodygoku2.getPosition().x - (GOKU2_WIDTH /2), this.bodygoku2.getPosition().y - (GOKU2_HEIGHT /2) );
        batch.draw(this.gokuTR, getX(),getY(), GOKU2_WIDTH, GOKU2_HEIGHT);
    }

    public void detach(){
        bodygoku2.destroyFixture(fixturegoku2);
        world.destroyBody(bodygoku2);
    }



}
