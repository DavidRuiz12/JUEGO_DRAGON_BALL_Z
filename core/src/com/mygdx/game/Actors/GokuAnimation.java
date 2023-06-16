package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

//Esta clase se crea exclusivamente para la creacion de la animacion de Goku al principio.
public class GokuAnimation extends Actor {
    //Creacion de las variables.
    private Animation<TextureRegion> animaciongoku;
    private Vector2 position;
    private World world;
    private Body bodygokuanimation;
    private float stateTime;

    //Creacion de la animacion.
    public GokuAnimation(World world, Animation<TextureRegion> animation, /*Sound sound,*/ Vector2 position){
        this.animaciongoku = animation;
        this.position = position;
        this.world = world;
        this.stateTime = 0f;

        createBody();
    }

    //Creacion del body
    private void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(this.position);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        this.bodygokuanimation = this.world.createBody(bodyDef);
    }

    //Metodo para dibujar la animacion en el mundo.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(bodygokuanimation.getPosition().x-0.4f, bodygokuanimation.getPosition().y-0.25f);
        batch.draw(this.animaciongoku.getKeyFrame(stateTime, true), getX(), getY(), 2f,2f );

        stateTime += Gdx.graphics.getDeltaTime();
    }

    //Metodo para la destruccion del cuerpo de la animacion.
    public void detach(){
        this.world.destroyBody(this.bodygokuanimation);
    }
}
