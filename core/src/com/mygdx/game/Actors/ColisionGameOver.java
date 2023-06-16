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

public class ColisionGameOver extends Actor {
    public static final float COLISION_ANCHO = 1f;
    public static final float COLISION_ALTO = 1f;

    public Body body;
    public Fixture fixture;
    private World world;
    private Vector2 vector2;
    private TextureRegion textureRegion;

    public ColisionGameOver(World wordl, Vector2 position){
        this.world = wordl;
        this.textureRegion = textureRegion;
        this.vector2 = position;

        createBody(position);
        createFixture();
    }

    private void createBody(Vector2 position) {
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);
    }

    private void createFixture(){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.7f,0.1f);
        this.fixture = body.createFixture(shape,8);
        shape.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition(this.body.getPosition().x - COLISION_ANCHO/2,this.body.getPosition().y - (COLISION_ALTO/2));

    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
