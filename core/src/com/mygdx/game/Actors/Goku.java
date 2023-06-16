package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Goku extends Actor {

    public static final float ATAQUE_ALTO = 1.5f;
    public static final float ATAQUE_ANCHO = 1f;

    private TextureRegion AtaqueTR;
    public Body bodyataque;
    public Fixture fixtureataque;
    private World world;

    public Goku(World world, Vector2 position){
        this.world = world;
        //this.AtaqueTR = trataque;

        //Instaciamos los metodos para la creacion del cuerpo y la fixture de ataquefuego.
        createBodyAtaque(position);

    }

    private void createBodyAtaque(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        bodyataque = world.createBody(def);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.bodyataque.getPosition().x - (ATAQUE_ANCHO/2), this.bodyataque.getPosition().y - (ATAQUE_ALTO/2) );
       // batch.draw(this.AtaqueTR, getX(),getY(),ATAQUE_ANCHO,ATAQUE_ALTO);
    }

    //Creacion del metodo detach para destruir el mundo y la fixture del enemigo.
    public void detach(){
        world.destroyBody(bodyataque);
    }


}
