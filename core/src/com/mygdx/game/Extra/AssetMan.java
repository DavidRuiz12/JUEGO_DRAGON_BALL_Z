package com.mygdx.game.Extra;

import static com.mygdx.game.Extra.Utils.ATAQUE;
import static com.mygdx.game.Extra.Utils.ATLAS_MAP;
import static com.mygdx.game.Extra.Utils.BACKGROUND_IMAGEN;
import static com.mygdx.game.Extra.Utils.BACKGROUND_PRINCIPAL;
import static com.mygdx.game.Extra.Utils.BOTON_START;
import static com.mygdx.game.Extra.Utils.ENEMIGO;
import static com.mygdx.game.Extra.Utils.ENEMIGO2;
import static com.mygdx.game.Extra.Utils.EXPLOSION;
import static com.mygdx.game.Extra.Utils.FLECHA1;
import static com.mygdx.game.Extra.Utils.FLECHA2;
import static com.mygdx.game.Extra.Utils.FONT_FNT;
import static com.mygdx.game.Extra.Utils.FONT_PNG;
import static com.mygdx.game.Extra.Utils.GAMEOVER;
import static com.mygdx.game.Extra.Utils.GOKU;
import static com.mygdx.game.Extra.Utils.GOKU1;

import static com.mygdx.game.Extra.Utils.GOKU2;
import static com.mygdx.game.Extra.Utils.GOKU3;
import static com.mygdx.game.Extra.Utils.GOKU4;
import static com.mygdx.game.Extra.Utils.GOKU5;
import static com.mygdx.game.Extra.Utils.GOKU6;
import static com.mygdx.game.Extra.Utils.GOKU7;


import static com.mygdx.game.Extra.Utils.GOKUIZQ;
import static com.mygdx.game.Extra.Utils.IMAGENGAMEOVERFONDO;
import static com.mygdx.game.Extra.Utils.MUSICA;
import static com.mygdx.game.Extra.Utils.MUSICA_GAMEOVER;
import static com.mygdx.game.Extra.Utils.MUSIC_START;
import static com.mygdx.game.Extra.Utils.PRUEBA;
import static com.mygdx.game.Extra.Utils.SOUND_ATACK;
import static com.mygdx.game.Extra.Utils.SOUND_EXPLOSION;
import static com.mygdx.game.Extra.Utils.SOUND_SSJ;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.w3c.dom.Text;

import java.net.PortUnreachableException;

public class AssetMan {
    private AssetManager assetManager;
    private TextureAtlas textureAtlas;

    public AssetMan(){
        this.assetManager = new AssetManager();
        assetManager.load(ATLAS_MAP,TextureAtlas.class);
        assetManager.load(SOUND_ATACK,Sound.class);
        assetManager.load(SOUND_EXPLOSION,Sound.class);
        assetManager.load(SOUND_SSJ,Music.class);
        assetManager.load(MUSICA, Music.class);
        assetManager.load(MUSIC_START,Music.class);
        assetManager.load(MUSICA_GAMEOVER,Music.class);
        assetManager.finishLoading();
        this.textureAtlas = assetManager.get(ATLAS_MAP);
    }

    //IMAGEN FLECHA 1
    public TextureRegion getflecha1(){
        return this.textureAtlas.findRegion(FLECHA1);
    }

    //IMAGEN DE FLECHA 2
    public TextureRegion getflecha2(){
        return this.textureAtlas.findRegion(FLECHA2);
    }

    //IMAGEN FONDO
    public TextureRegion getescenario(){
        return this.textureAtlas.findRegion(BACKGROUND_IMAGEN);
    }

    //IMAGEN DE FONDO PRINCIPAL
    public TextureRegion getstart() {
        return this.textureAtlas.findRegion(BACKGROUND_PRINCIPAL);
    }

    //IMAGEN DE BOTON START
    public TextureRegion getbotonstart(){
        return this.textureAtlas.findRegion(BOTON_START);
    }

    //ANIMACION GOKU
    public Animation<TextureRegion> getgokuanimation(){
        return new Animation<TextureRegion>(0.40f,
                textureAtlas.findRegion(GOKU2),
                textureAtlas.findRegion(GOKU3),
                textureAtlas.findRegion(GOKU4),
                textureAtlas.findRegion(GOKU2),
                textureAtlas.findRegion(GOKU3),
                textureAtlas.findRegion(GOKU4),
                textureAtlas.findRegion(GOKU5),
                textureAtlas.findRegion(GOKU6),
                textureAtlas.findRegion(GOKU7),
                textureAtlas.findRegion(GOKU5),
                textureAtlas.findRegion(GOKU6),
                textureAtlas.findRegion(GOKU7));

    }
    //IMAGEN DE SCORE
    public BitmapFont getFont(){
        return new BitmapFont(Gdx.files.internal(FONT_FNT),Gdx.files.internal(FONT_PNG), false);
    }

    //IMAGEN DE GOKU
    public TextureRegion getgokusprite(){
        return this.textureAtlas.findRegion(GOKU);
    }

    //IMAGEN DE GOKU2
    public TextureRegion getgokusprite2(){
        return this.textureAtlas.findRegion(GOKUIZQ);
    }

    //IMAGEN DE ATAQUE
    public TextureRegion getataquesprite(){
        return this.textureAtlas.findRegion(ATAQUE);
    }

    //IMAGEN DE ENEMIGO
    public TextureRegion getenemigoataque(){
        return this.textureAtlas.findRegion(ENEMIGO);
    }

    //IMAGEN DE ENEMIGO2
    public TextureRegion getenemigo2(){
        return this.textureAtlas.findRegion(ENEMIGO2);
    }


    //IMAGEN DE GAMEOVERFONDO
    public TextureRegion getimagenfondoGameover(){
        return this.textureAtlas.findRegion(IMAGENGAMEOVERFONDO);
    }

    //IMAGEN GAMEOVER
    public TextureRegion getGameover(){
        return this.textureAtlas.findRegion(GAMEOVER);
    }

    //SONIDO DE ATAQUE
    public Sound getataquesonido(){
        return this.assetManager.get(SOUND_ATACK);
    }

    //SONIDO DE EXPLOSION
    public Sound getexplosionsonido(){
        return this.assetManager.get(SOUND_EXPLOSION);
    }

    //SONIDO DE TRANSFORMACION
    public Music gettransformacionsonido(){
        return this.assetManager.get(SOUND_SSJ);
    }

    //MUSICA START
    public Music getMusicStart(){
        return this.assetManager.get(MUSIC_START);
    }

    //MUSICA DEL VIDEOJUEGO
    public Music getMusic(){
        return this.assetManager.get(MUSICA);
    }

    //MUSICA GAMEOVER
    public Music getmusigameover(){
        return this.assetManager.get(MUSICA_GAMEOVER);
    }

    public TextureRegion getget (){
        return this.textureAtlas.findRegion(PRUEBA);
    }
}
