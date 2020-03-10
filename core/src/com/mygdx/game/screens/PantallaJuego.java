package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.model.*;
import com.mygdx.game.util.Animaciones;
import com.mygdx.game.util.Constantes;

import java.util.Random;

public class PantallaJuego implements Screen {
    private SpriteBatch batch;
    private Nave jugador;
    private Array<Enemigo> marcianos;
    private Array<Roca> rocas;
    private Array<Bala> balas;
    private long tiempoMarciano;
    private long tiempoRoca;
    private long tiempoAumenta;
    private int movNAve =1;
    private int contaVidas=0;
    private int contaMarcianos=0;
    BitmapFont font;
    BitmapFont fontMarciano;
    BitmapFont fontBoss;
    private Fondo fondo;
    private  Fondo fondoAux;
    int vidaMarcianos = Constantes.VIDA_MARCIANOS;
    int vidaRocas = Constantes.VIDA_ROCA;
    int velMarcianos;
    int velRocas;
    private long tiempoRotacionRoca;
    float moverFondo;
    float moverFondoAux;
    float fondoAumenta;
    Sound soniDisparo;
    Sound meDieron;
    long constEntreMarcionos;
    long constEntreRocas;
    float rotaFondo;
    Boss elBoss;
    Animaciones misAnimaciones;
    float stateTime;
    int frameExplo=0;
    Array<Explosion> arrayExplosiones;
    Sound backSong;
    int dificultad;
    int constMarcianosBoss;
    boolean lvlComplete=false;


    public PantallaJuego(int dificultad) {
        this.dificultad = dificultad;
        misAnimaciones = new Animaciones();
        fondo = new Fondo(new Texture(Constantes.IMAGEN_FONDO),moverFondo,0,rotaFondo);
    }

    public PantallaJuego() {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        if(jugador==null){

            velRocas = (Constantes.VELOCIDAD_ROCAS*dificultad);
            velMarcianos = Constantes.VELOCIDAD_MARCIANOS*dificultad;
            jugador = new Nave(new Vector2(10,200),misAnimaciones.getNaveTexturas().get(0),3,5);
            moverFondo=0;
            rotaFondo = Constantes.ROTA_FONDO;

            moverFondoAux=fondo.getFondo().getWidth();
          //  fondoAux = new Fondo ( new Texture(Constantes.IMAGEN_FONDO),moverFondoAux,0,rotaFondo);
            marcianos = new Array<>();

            constMarcianosBoss= Constantes.MARCIANOS_PARA_BOSS*dificultad;

            contaVidas=jugador.getVidas();
            tiempoMarciano= TimeUtils.millis();
            tiempoRoca = TimeUtils.millis();
            tiempoAumenta = TimeUtils.millis();
            tiempoRotacionRoca = TimeUtils.millis();
            fondoAumenta = Constantes.AUMENTA_FONDO;
            constEntreMarcionos =Constantes.TIEMPO_ENTRE_MARCIANOS+100-(100*dificultad);
            constEntreRocas = Constantes.TIEMPO_ENTRE_ROCAS-(100*dificultad);

            soniDisparo = Gdx.audio.newSound(Gdx.files.internal("sounds/disparo.mp3"));
            meDieron = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.wav"));
            backSong = Gdx.audio.newSound(Gdx.files.internal("sounds/the-thrill-wiz-khalifa.mp3"));
            backSong.play();
            stateTime= 0f;
            arrayExplosiones = new Array<>();


            elBoss= new Boss(new Texture("enemy/voxBoss3.png"),Constantes.VIDAS_BOSS,Constantes.VELOCIDAD_BOSS);
            int enX = Gdx.graphics.getWidth()-elBoss.getTextura().getWidth();
            int enY = MathUtils.random(0,Gdx.graphics.getHeight());
            elBoss.setVecConRec(new Vector2(enX,enY));





            rocas = new Array<>();
            balas = new Array<>();

            font = new BitmapFont();

            font.setColor(Color.CYAN);
            fontMarciano = new BitmapFont();
            fontMarciano.setColor(Color.CYAN);
            fontBoss = new BitmapFont();
            fontBoss.setColor(Color.CYAN);


        }


    }

    public Sound getBackSong() {
        return backSong;
    }

    public void setBackSong(Sound backSong) {
        this.backSong = backSong;
    }

    @Override
    public void render(float delta) {
        actualizar();
        pintar();

    }
    private void actualizar(){
        // input de usuario
        if(jugador.getVidas()>0){
            if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                jugador.moverArriba();

            }
            if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
                jugador.moverDerecha();

            }
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
                jugador.moverIzquierda();

            }
            if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
                jugador.moverAbajo();
             }
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaPause(this));

            }
            if(lvlComplete){
                if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    nextLvl();
                }
            }

        }else{
            if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                actualizarMuerte();
            }
        }
        moverFondo();
        if(!lvlComplete){
            enemigosYBoss();
        }

        moverEnemigos();
        comprobarColisiones();
        disparar();
        moverBalas();
        logros();
        cambiaTexturaExpl();
        // texBoss();

    }
    private void enemigosYBoss(){
        if(contaMarcianos<=constMarcianosBoss){
            generarEnemigos();
        }else{
            //generaBoss();
            lvlComplete=true;
        }

    }
    private void nextLvl(){
        backSong.stop();
        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego2(dificultad+1));

    }
    private void generaBoss(){
        if(!elBoss.isVivo()){
            elBoss.setVivo(true);
        }
        if((jugador.getPosicion().x-elBoss.getPosicion().x)<0){
            elBoss.moverIzquierda();
        }else{
            if((jugador.getPosicion().x-elBoss.getPosicion().x)>0){
                elBoss.moverDerecha();
            }
        }
        if((jugador.getPosicion().y-elBoss.getPosicion().y)<0){
            elBoss.moverAbajo();
        }else{
            if((jugador.getPosicion().y-elBoss.getPosicion().y)>0){
                elBoss.moverArriba();
            }
        }
        generarRocas(Constantes.TIEMPO_ENTRE_ROCAS);

    }

    private void generarRocas(long constanteRocas){
        int enX=0;
        int enY=0;
        if(TimeUtils.millis()- tiempoRoca> constanteRocas){
            Texture texRoca = new Texture("enemy/stone1.png");
            enX= Gdx.graphics.getWidth()-texRoca.getWidth();
            enY=MathUtils.random(0,Gdx.graphics.getHeight());
            Roca roca = new Roca(new Vector2(enX,enY),texRoca,vidaRocas,velRocas);
            rocas.add(roca);
            tiempoRoca=TimeUtils.millis();

        }
    }

    private void generarEnemigos(){
        Random r = new Random();
        int enX=0;
        int enY=0;
        if(marcianos.size<=35){
            if(TimeUtils.millis()-tiempoAumenta>Constantes.TIEMPO_MAS_RAPIDO){
                velMarcianos++;
                velRocas++;
                //fondoAumenta++;
                tiempoAumenta=TimeUtils.millis();
                constEntreRocas=constEntreRocas-50;
                constEntreMarcionos=constEntreMarcionos-50;

            }

            if(TimeUtils.millis() -tiempoMarciano> constEntreMarcionos){

                Texture textura = new Texture("enemy/e_f1.png");
                enX= Gdx.graphics.getWidth()-textura.getWidth();
                enY= MathUtils.random(0,Gdx.graphics.getHeight());

                Enemigo marciano = new Enemigo(new Vector2(enX,enY),new Texture("enemy/e_f1.png"),vidaMarcianos,velMarcianos);
                marcianos.add(marciano);
                tiempoMarciano=TimeUtils.millis();

            }

        }
        generarRocas(constEntreRocas);
    }

    private void  disparar(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            soniDisparo.play();
            int x = (int) (jugador.getPosicion().x+jugador.getTextura().getWidth());
            int y = (int) jugador.getPosicion().y ;
            Bala bala = new Bala(new Vector2(x,y),new Texture("ship/bullet.png"),1,10);
            balas.add(bala);
        }
    }
    private void moverBalas(){
        if(balas.size>0){
            for(Bala bala : balas){
                bala.moverDerecha();
            }
        }
    }

    private void generarExplosiones(Vector2 posicion){
        Explosion explosion = new Explosion(misAnimaciones.getArraySCIFI().get(0),posicion);
        arrayExplosiones.add(explosion);
    }


    private void comprobarColisiones(){
        for(Enemigo marciano : marcianos){
            if(jugador.rect.overlaps(marciano.rect)){
                marciano.quitarVida();
                generarExplosiones(marciano.getPosicion());
                if(marciano.getVidas()<=0){
                    marcianos.removeValue(marciano,true);
                }
                if(!jugador.isInmune()){
                    meDieron.play();
                    jugador.quitarVida();
                    jugador.setInmune(true);

                }
            }else{
                if(jugador.isInmune()){
                    jugador.setInmune(false);
                }
            }
            if(jugador.getVidas()<=0){

            }
            for(Bala bala : balas){
                if (bala.rect.overlaps(marciano.rect)) {

                    marciano.quitarVida();
                    if(marciano.getVidas()<=0){
                        generarExplosiones(marciano.getPosicion());
                        marcianos.removeValue(marciano,true);
                        balas.removeValue(bala,true);
                        contaMarcianos++;
                    }
                }
            }
        }
        for(Roca roca: rocas){
            if(jugador.rect.overlaps((roca.rect))){
                generarExplosiones(jugador.getPosicion());
                meDieron.play();
                jugador.setVidas(0);
            }

            for(Bala bala : balas){
                if(bala.rect.overlaps(roca.rect)){
                    balas.removeValue(bala,true);
                    //roca.quitarVida();
                    /*
                    if(roca.getVidas()==0){
                        rocas.removeValue(roca,true);
                    }*/
                }
            }
        }
        if(elBoss.isVivo()){
            for(Bala bala : balas){
                if(bala.rect.overlaps(elBoss.rect)){
                    elBoss.quitarVida();
                }
            }

        }
        contaVidas=jugador.getVidas();
    }
    private void moverEnemigos(){

        for(Enemigo marciano : marcianos){
            marciano.moverIzquierda();

            if(marciano.getPosicion().x<=0.5){
                marcianos.removeValue(marciano,true);
            }
        }
        for(Roca roca : rocas){
            roca.moverIzquierda();
            if(roca.getPosicion().x<=0.5){
                rocas.removeValue(roca,true);
            }
        }
    }
    public void cambiarTexturaMarcianos(){
        for(Enemigo marciano:marcianos){
            int posi = marciano.getPosiTextura();
            switch (posi){
                case 1: marciano.setTextura(misAnimaciones.getMarciTexturas().get(0));
                    break;
                case 2: marciano.setTextura(misAnimaciones.getMarciTexturas().get(1));
                    break;
                case 3: marciano.setTextura(misAnimaciones.getMarciTexturas().get(2));
                    break;
                case 4: marciano.setTextura(misAnimaciones.getMarciTexturas().get(3));
                    break;
                case 5: marciano.setTextura(misAnimaciones.getMarciTexturas().get(4));
                    break;
                case 6: marciano.setTextura(misAnimaciones.getMarciTexturas().get(5));
                    break;
            }
            posi++;
            if(posi>6){
                posi=1;
            }
            marciano.setPosiTextura(posi);
        }

    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int cambiaTexturaNave(Nave nave, int i){

        if(jugador.getVidas()>0){
            switch (i){
                case 1:nave.setTextura(misAnimaciones.getNaveTexturas().get(0));
                    break;
                case 2: nave.setTextura(misAnimaciones.getNaveTexturas().get(1));
                    break;
                case 3: nave.setTextura(misAnimaciones.getNaveTexturas().get(2));
                    break;
                case 4:nave.setTextura(misAnimaciones.getNaveTexturas().get(3));
                    break;
            }
            i++;
            if(i>4){
                i=1;
            }
        }else{
            nave.setTextura(misAnimaciones.getArraySCIFI().get(1));
        }

        return i;
    }
    public void rotarRocas(Array<Roca> rocas){
        int posi;

        if(TimeUtils.millis()-tiempoRotacionRoca>Constantes.TIEMPO_ROTACION_ROCA){
            for(Roca roca:rocas){
                posi = roca.getPosiTextura();
                switch (posi){
                    case 1:roca.setTextura(misAnimaciones.getRocaTexturas().get(0));
                        break;
                    case 2: roca.setTextura(misAnimaciones.getRocaTexturas().get(1));
                        break;
                    case 3: roca.setTextura(misAnimaciones.getRocaTexturas().get(2));
                        break;
                    case 4: roca.setTextura(misAnimaciones.getRocaTexturas().get(3));
                        break;
                    case 5: roca.setTextura(misAnimaciones.getRocaTexturas().get(4));
                        break;
                }
                posi++;
                if(posi>5){
                    posi=1;
                }
                roca.setPosiTextura(posi);
                tiempoRotacionRoca=TimeUtils.millis();
            }
        }
    }
    private void logros(){
        if((contaMarcianos%50==0)&& (contaMarcianos!=0)){
            jugador.setVidas(jugador.getVidas()+1);
            contaMarcianos++;
        }
    }
    private void actualizarMuerte(){

        rocas.clear();
        marcianos.clear();
        backSong.stop();
        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrincipal());

    }

    private void moverFondo (){
        /*
        moverFondo=moverFondo-fondoAumenta;
        fondo.setX(moverFondo);
        moverFondoAux=moverFondoAux-fondoAumenta;
        fondoAux.setX(moverFondoAux);
        if((fondo.getFondo().getWidth()+fondo.getX())==0){
            moverFondo=fondoAux.getX()+fondoAux.getFondo().getWidth();
        }
        if((fondoAux.getFondo().getWidth()+fondoAux.getX())== 0){
            moverFondoAux=fondo.getX()+fondo.getFondo().getWidth();
        }*/
        fondo.setRotacion((float) (fondo.getRotacion()-0.1));
    }
    private void texBoss(){
        if(!elBoss.isVivo()){
            elBoss.setTextura(misAnimaciones.getMuerteBoss());
        }
    }
    private void cambiaTexturaExpl(){
        for(Explosion explo : arrayExplosiones){
            if(explo.getFrameActual()>=misAnimaciones.getArraySCIFI().size()){
                explo.setFrameActual(0);
            }
            explo.setTextura(misAnimaciones.getArraySCIFI().get(explo.getFrameActual()));
        }
    }

    private  void pintar(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        fondo.pintaFondoRotando(batch);
        //fondoAux.pintaFondo(batch);
        movNAve=cambiaTexturaNave(jugador,movNAve);
        if(elBoss.isVivo()){
            elBoss.mePinto(batch);
        }else{
            for(Enemigo marciano: marcianos){
                marciano.mePinto(batch);
            }

        }
        rotarRocas(rocas);
        for (Roca roca : rocas){
            roca.mePinto(batch);
        }
        jugador.mePinto(batch);

        cambiarTexturaMarcianos();

        for (Bala bala : balas){
            bala.mePinto(batch);

        }
       for(Explosion explo: arrayExplosiones){
           explo.mePinto(batch);
           explo.sumarFrame();
           if(explo.getFrameActual()>=misAnimaciones.getArraySCIFI().size() && jugador.getVidas()>0 ){
               arrayExplosiones.removeValue(explo,true);
           }
       }
       if(jugador.getVidas()<=0){
           batch.draw(misAnimaciones.getGameOver(),Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/2);
           batch.draw(misAnimaciones.getPaktc(),Gdx.graphics.getWidth()/4-50,
                   Gdx.graphics.getHeight()/2-50);
       }
       if(lvlComplete){
           batch.draw(misAnimaciones.getLvlc(),Gdx.graphics.getWidth()/9.5f,Gdx.graphics.getHeight()/2);
           batch.draw(misAnimaciones.getPaktc(),Gdx.graphics.getWidth()/4-50,
                   Gdx.graphics.getHeight()/2-50);
       }

        font.draw(batch, "Vidas: "+String.valueOf(contaVidas), 50, 20);
        fontMarciano.draw(batch,"Marcianos muertos: "+String.valueOf(contaMarcianos),200,20);
        fontBoss.draw(batch,"marcianos: "+String.valueOf(marcianos.size),Gdx.graphics.getWidth()-200,20);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
