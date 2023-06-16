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

public class Enemigo2 extends Actor {
    //Indicamos el tamaño del sprite del enemigo
    public static final float ENEMIGO2_WIDTH = 2f;
    private static final float ENEMIGO2_HEIGHT = 2f;
    private static final float SPEED = 1f;

    //Creamos las variables de TextureRegion, el Body el Fixture y el mundo.
    private TextureRegion ENEMIGOTR2;
    public Body bodyenemigo2;
    public Fixture fixtureenemigo2;
    private World world;

    //Creamos el constructor de enemigo al que le pasamos el mundo el TextureRegion y la posicion donde ira el Srpite.
    public Enemigo2 (World world, TextureRegion trenemigo, Vector2 position){
        this.world = world;
        this.ENEMIGOTR2 = trenemigo;

        //Inicializamos en el constructor los metodos para la creacion del Body y la creacion del Fixture.
        createBodyEnemigo(position);
        createFixtureEnemigo();
    }

    private void createFixtureEnemigo() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 1f);
        this.fixtureenemigo2 = bodyenemigo2.createFixture(shape,8);
        shape.dispose();
    }

    private void createBodyEnemigo(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        bodyenemigo2 = world.createBody(def);
        bodyenemigo2.setLinearVelocity(SPEED,0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    //Sobreecargamos el metodo draw para dibujar los sprite en pantalla.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyenemigo2.getPosition().x - (ENEMIGO2_WIDTH /2), this.bodyenemigo2.getPosition().y - (ENEMIGO2_HEIGHT /2) );
        batch.draw(this.ENEMIGOTR2, getX(),getY(), ENEMIGO2_WIDTH, ENEMIGO2_HEIGHT);

    }

    //Creacion del metodo detach para destruir el mundo y la fixture del enemigo.
    public void detach(){
        bodyenemigo2.destroyFixture(fixtureenemigo2);
        world.destroyBody(bodyenemigo2);
    }
}
