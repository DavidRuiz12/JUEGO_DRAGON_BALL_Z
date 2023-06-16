package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Explosion extends Actor {
    //Indicamos el tamaño del sprite del enemigo
    public static final float EXPLOSION_WIDTH = 2f;
    private static final float EXPLOSION_HEIGHT = 2f;

    //Creamos las variables de TextureRegion, el Body el Fixture y el mundo.
    private TextureRegion EXPLOSIONTR;
    public Body bodyexplosion;
    private World world;

    //Creamos el constructor de enemigo al que le pasamos el mundo el TextureRegion y la posicion donde ira el Srpite.
    public Explosion (World world, TextureRegion trexplosion, Vector2 position){
        this.world = world;
        this.EXPLOSIONTR = trexplosion;

        //Inicializamos en el constructor los metodos para la creacion del Body y la creacion del Fixture.
        createBodyEnemigo(position);
    }

    private void createBodyEnemigo(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        bodyexplosion = world.createBody(def);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    //Sobreecargamos el metodo draw para dibujar los sprite en pantalla.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyexplosion.getPosition().x - (EXPLOSION_WIDTH /2), this.bodyexplosion.getPosition().y - (EXPLOSION_HEIGHT /2) );
        batch.draw(this.EXPLOSIONTR, getX(),getY(), EXPLOSION_WIDTH, EXPLOSION_HEIGHT);

    }

    //Creacion del metodo detach para destruir el mundo y la fixture del enemigo.
    public void detach(){
        world.destroyBody(bodyexplosion);
    }
}
