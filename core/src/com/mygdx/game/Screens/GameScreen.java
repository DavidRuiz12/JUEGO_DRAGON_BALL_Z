package com.mygdx.game.Screens;

import static com.mygdx.game.Extra.Utils.SCREEN_HEIGHT;
import static com.mygdx.game.Extra.Utils.SCREEN_WIDHT;
import static com.mygdx.game.Extra.Utils.WORLD_HEIGHT;
import static com.mygdx.game.Extra.Utils.WORLD_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Actors.Goku;
import com.mygdx.game.Actors.ColisionGameOver;
import com.mygdx.game.Actors.Enemigo;
import com.mygdx.game.Actors.Enemigo2;
import com.mygdx.game.Actors.ataquefuego;
import com.mygdx.game.MainGame;

public class GameScreen extends BaseScreen  {

    //Definicion de los atributos
    private Stage stage;
    private ataquefuego ataquefuego;
    private Goku ataque;
    private Enemigo enemigo;
    private Enemigo2 enemigo2;
    private Image background;
    private Image flecha1;
    private Image flecha2;
    private Image gameover;
    private Image gokuizq;
    private Image gokuder;
    private Image muestra;
    private World world;
    private Sound sonidoexplosion;
    private Music musicavideojuego;
    private Music musicagameover;
    private float positionx = 2.5f;
    private float positiony = 1.5f;
    private boolean vuelve = false;
    private boolean eliminarenemigo1 = false;
    private boolean eliminarenemigo2 = false;
    private boolean repetirposicion = false;
    public boolean cambiar = false;
    private ColisionGameOver colisionGameOver;
    private int numeroscore = 0;


    //Depuracion
    private Box2DDebugRenderer debugRenderer;

    //Creacion de las camaras del videojuego.
    private OrthographicCamera worldcamera;
    private OrthographicCamera fontCamera;

    //Declarion de font del score
    private BitmapFont score;


    //Constructor donde creamos el nuevo mundo y la vista del mundo. Ademas se inicializan los sonidos y el score.
    public GameScreen(MainGame mainGame) {
        super(mainGame);

        this.world = new World(new Vector2(0, 0), true);
        FitViewport fitViewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT);
        this.stage = new Stage(fitViewport);
        sonidoexplosion = mainGame.assetManager.getexplosionsonido();
        añadirScore();


        this.musicavideojuego = this.mainGame.assetManager.getMusic();
        musicagameover = mainGame.assetManager.getmusigameover();
        this.worldcamera = (OrthographicCamera) this.stage.getCamera();
        this.debugRenderer = new Box2DDebugRenderer();

    }

    //Metodo para añadir el escenario.
    public void añadirescenario(){
        //Se crea una nueva imagen y se coge la imagen del assetmanager con el metodo getescenario.
        this.background = new Image(mainGame.assetManager.getescenario());
        //Se indica la posicion donde se va a poner dicha imagen.
        this.background.setPosition(0,0);
        //Se indica el tamaño que va a tener la imagen.
        this.background.setSize(WORLD_WIDTH,WORLD_HEIGHT);
        //Se añade el actor.
        this.stage.addActor(this.background);
    }

    public void añadirflecha1(){
        this.flecha1 = new Image(mainGame.assetManager.getflecha1());
        this.flecha1.setPosition(0,0.5f);
        this.flecha1.setSize(1,1);
        this.stage.addActor(this.flecha1);
    }


    public void añadirflecha2(){
        this.flecha2 = new Image(mainGame.assetManager.getflecha2());
        this.flecha2.setPosition(3.5f,0.5f);
        this.flecha2.setSize(1,1);
        this.stage.addActor(this.flecha2);
    }


    public void colision(){
        this.colisionGameOver = new ColisionGameOver(this.world,new Vector2(2f,2.5f));
        this.stage.addActor(this.colisionGameOver);
    }


    //Metodo que se encarga de crear un actor.
    public void añadirataquegoku(){
        //Se crea y se le añade una textura a nuestro actor, que la cogemos del assetmanager por el metodo get.
        TextureRegion texturegoku = mainGame.assetManager.getataquesprite();
        //Se añade el sonid de ataque.
        Sound sound = mainGame.assetManager.getataquesonido();
        //Se crea el ataque al mundo, en la posicion que nosotros le indiquemos, la texture que hemos guardado y el sonido.
        this.ataquefuego = new ataquefuego(this.world,texturegoku,new Vector2(positionx,positiony),sound);
        //Se añade el actor al stage.
        this.stage.addActor(this.ataquefuego);
    }

    public void añadirgokuizq(){
        this.gokuizq = new Image(mainGame.assetManager.getgokusprite2());
        this.gokuizq.setPosition(1.5f,0.8f);
        this.gokuizq.setSize(1,1.5f);
        this.stage.addActor(this.gokuizq);
    }

    public void añadirgokuder(){
        this.gokuder = new Image(mainGame.assetManager.getgokusprite());
        this.gokuder.setPosition(1.5f,0.8f);
        this.gokuder.setSize(1,1.5f);
        this.stage.addActor(this.gokuder);
    }

    public void añadirmuestra(){
        this.muestra = new Image(mainGame.assetManager.getgokusprite());
        this.muestra.setPosition(1.5f,0.8f);
        this.muestra.setSize(1,1.5f);
        this.stage.addActor(this.muestra);
    }

    //Metodo que se encarga de mover el ataque.
    public void moverataque(){
        this.ataquefuego.bodygoku.setTransform(positionx,positiony,this.ataquefuego.bodygoku.getAngle());
    }

    public void añadirenemigo(){
        TextureRegion textureenemigo = mainGame.assetManager.getenemigoataque();
        this.enemigo = new Enemigo(this.world,textureenemigo,new Vector2(8f,1.5f));
        this.stage.addActor(this.enemigo);
    }

    public void añadirenemigo2(){
        TextureRegion textureenemigo2 = mainGame.assetManager.getenemigo2();
        this.enemigo2 = new Enemigo2(this.world,textureenemigo2,new Vector2(-6f,1.5f));
        this.stage.addActor(this.enemigo2);
    }

    public void añadiragoku(){
        TextureRegion textureataque = mainGame.assetManager.getgokusprite();
        this.ataque = new Goku(this.world,new Vector2(2f,1.5f));
        this.stage.addActor(this.ataque);
    }

    private void añadirScore(){
        this.score = this.mainGame.assetManager.getFont();
        this.score.getData().scale(1f);
        this.fontCamera = new OrthographicCamera();
        this.fontCamera.setToOrtho(false, SCREEN_WIDHT,SCREEN_HEIGHT);
        this.fontCamera.update();
    }



//Metodo render que se ejcuta 60 veves por segundo.
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.stage.getBatch().setProjectionMatrix(worldcamera.combined);
        this.stage.act();
        this.world.step(delta,6,2);
        this.stage.draw();
        this.debugRenderer.render(this.world,this.worldcamera.combined);

        this.stage.getBatch().setProjectionMatrix(this.fontCamera.combined);
        this.stage.getBatch().begin();
        this.score.draw(this.stage.getBatch(),""+numeroscore, 200, 575);
        this.stage.getBatch().end();

        if (vuelve == true){
            moverataque();
            this.ataquefuego.bodygoku.setLinearVelocity(0,0);
            vuelve = false;
        }

        //Metodo que comprueba si el objeto de ataque esta fuera de la pantalla, si es asi este vuelve a su posicion inicial.
        if (this.ataquefuego.isOutOfScreen() || this.ataquefuego.isOutOfScreennegative()){
            moverataque();
            this.ataquefuego.bodygoku.setLinearVelocity(0,0);
            vuelve = false;

        }

        //Si la variable eliminarenemigo1 es igual a true, el enemigo  se mueve fuara de la pantalla y vueleve a iniciar el recorrido, para dar la sensacion de destruccion.
        if (eliminarenemigo1 == true){
            enemigo.bodyenemigo.setTransform(8f,1.5f,enemigo.bodyenemigo.getAngle());
            //Ademas se le vueve a indicar la velocidad al enemigo.
            enemigo.bodyenemigo.setLinearVelocity(-1,0);
            eliminarenemigo1 = false;
        }

        if (eliminarenemigo2 == true){
            enemigo2.bodyenemigo2.setTransform(-2f,1.5f,enemigo2.bodyenemigo2.getAngle());
            enemigo2.bodyenemigo2.setLinearVelocity(1,0);
            eliminarenemigo2 = false;
        }

        //Si la variable cambiar es igual a true se cambia la pantalla.
        if(cambiar == true){
            mainGame.setScreen(mainGame.gameOverScreen);
        }

        //Si la variable ataquefuego que contiene la varibable booleana que devuelve la clase ataque es igual a true
        //deja de ser visible el sprite de gokuder y se pone visible el sprite de gokuizq, para dar la sensacion de rotacion.
        if (ataquefuego.cambiopossizq == true) {
            gokuder.setVisible(false);
            gokuizq.setVisible(true);
            //Se vuelve a inicializar a false.
            ataquefuego.cambiopossizq = false;
            //Se elemina la imagen de muestra.
            muestra.remove();
            //Se hace visible en ataque fuego
            ataquefuego.setVisible(true);
        }

        if(ataquefuego.cambiopossder == true){
            gokuder.setVisible(true);
            gokuizq.setVisible(false);
            ataquefuego.cambiopossder = false;
            muestra.remove();
            ataquefuego.setVisible(true);
        }

        Gdx.input.setInputProcessor(ataquefuego);

    }

    //Metodo show que se encarga de mostrar, lo que vamos a necesitar el nuestro juego, solo lo hace una vez que es cuando se inicia el juego.
    @Override
    public void show() {
            añadirescenario();
            añadirataquegoku();
            añadiragoku();
            añadirenemigo();
            añadirenemigo2();
            colision();
            añadirflecha1();
            añadirflecha2();
            añadirgokuder();
            añadirgokuizq();
            añadirmuestra();
            gokuizq.setVisible(false);
            gokuder.setVisible(false);
            ataquefuego.setVisible(false);

            musicavideojuego.setLooping(true);
            musicavideojuego.play();


//Metodo que se encarga de comprobar las colisiones que va a ver dentro del videjuego.
    world.setContactListener(new ContactListener() {
        @Override
        public void beginContact(Contact contact) {
            //Tenemos la fixture a y la fixtureb
            Fixture fixtureA = contact.getFixtureA();
            Fixture fixtureB = contact.getFixtureB();

            //Si la fixturea colisiona con la fixtureb.
                if (fixtureA == ataquefuego.fixturegoku && fixtureB == enemigo.fixtureenemigo) {
                    //La variable se inicializa a true.
                    eliminarenemigo1 = true;
                    //Se ejecuta el sonido de la explosion
                    sonidoexplosion.play();
                    //Se añade uno a score
                    numeroscore += 1;
                    //La variable se inicializa a false.
                    ataquefuego.setVisible(false);

                }if (fixtureA == enemigo.fixtureenemigo && fixtureB == ataquefuego.fixturegoku) {
                    eliminarenemigo1 = true;
                    sonidoexplosion.play();
                    numeroscore += 1;
                    ataquefuego.setVisible(false);

                }if (fixtureA == ataquefuego.fixturegoku && fixtureB == enemigo2.fixtureenemigo2){
                    eliminarenemigo2 = true;
                    sonidoexplosion.play();
                    numeroscore += 1;
                    ataquefuego.setVisible(false);

                }if(fixtureA == enemigo2.fixtureenemigo2 && fixtureB == ataquefuego.fixturegoku ) {
                eliminarenemigo2 = true;
                sonidoexplosion.play();
                numeroscore += 1;
                ataquefuego.setVisible(false);

                //Si la colision de nuestro personaje colisiona de nuestro enemigo.
            }if(fixtureA == colisionGameOver.fixture && fixtureB == enemigo.fixtureenemigo ){
                    //Se para la musica de el videojuego,
                    musicavideojuego.stop();
                    //Se paran los dos enemigos
                    enemigo.bodyenemigo.setLinearVelocity(0,0);
                    enemigo2.bodyenemigo2.setLinearVelocity(0,0);
                    //Se inicializa la varaible booleana a true, que se encarga de cambiar a la pantalla de game over.
                    cambiar = true;


            }if(fixtureA == enemigo.fixtureenemigo&& fixtureB == colisionGameOver.fixture ) {
                musicavideojuego.stop();
                enemigo.bodyenemigo.setLinearVelocity(0,0);
                enemigo2.bodyenemigo2.setLinearVelocity(0,0);
                cambiar = true;

            }
            if(fixtureA == enemigo2.fixtureenemigo2&& fixtureB == colisionGameOver.fixture ) {
                musicavideojuego.stop();
                enemigo.bodyenemigo.setLinearVelocity(0, 0);
                enemigo2.bodyenemigo2.setLinearVelocity(0, 0);
                cambiar = true;

            }
            if(fixtureA == colisionGameOver.fixture && fixtureB == enemigo2.fixtureenemigo2 ) {
                musicavideojuego.stop();
                enemigo.bodyenemigo.setLinearVelocity(0, 0);
                enemigo2.bodyenemigo2.setLinearVelocity(0, 0);
                cambiar = true;
            }

            vuelve = true;

        }

        @Override
        public void endContact(Contact contact) {

        }

        @Override
        public void preSolve(Contact contact, Manifold oldManifold) {

        }

        @Override
        public void postSolve(Contact contact, ContactImpulse impulse) {

        }


    });


    }

    //Metodo hide para hacer el remove de los actores y musica
    @Override
    public void hide() {
        this.ataque.remove();
        this.ataque.detach();
        this.enemigo.detach();
        this.enemigo.remove();
        this.ataquefuego.detach();
        this.ataquefuego.remove();
        this.musicavideojuego.stop();
        this.gokuder.remove();
        this.gokuizq.remove();
        this.muestra.remove();
        this.flecha1.remove();
        this.flecha2.remove();


    }


    //Metodo dispose para eliminar el stage y el word.
    @Override
    public void dispose() {
        this.stage.dispose();
        this.world.dispose();

    }


}
