package com.mygdx.game.model;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Personaje {
    private Vector2 posicion;
    private Texture textura;
    private int vidas;
    private int velocidad;
    public Rectangle rect;
    private  int posiTextura;


    public Personaje(Vector2 posicion, Texture textura, int vidas, int velocidad) {
        this.posicion = posicion;
        this.textura = textura;
        this.vidas = vidas;
        this.velocidad = velocidad;
        posiTextura=1;

        rect = new Rectangle(getPosicion().x,getPosicion().y,textura.getWidth(),textura.getHeight());

    }
    public Personaje(Texture textura, int vidas, int velocidad) {

        this.textura = textura;
        this.vidas = vidas;
        this.velocidad = velocidad;
        posiTextura=1;



    }

    public void setVecConRec(Vector2 posicion){
        this.posicion=posicion;
        rect = new Rectangle(getPosicion().x,getPosicion().y,textura.getWidth(),textura.getHeight());
    }


    public  void mePinto(SpriteBatch batch){
        batch.draw(getTextura(), getPosicion().x, getPosicion().y);
    }


    public void moverIzquierda(){
        if(getPosicion().x>=0 ){
            getPosicion().x= getPosicion().x-getVelocidad();
            rect.set(getPosicion().x,getPosicion().y,textura.getWidth(),textura.getHeight());
        }

    }

    public void moverDerecha(){
        if(getPosicion().x<=Gdx.graphics.getWidth()){
            getPosicion().x = getPosicion().x+getVelocidad();
            rect.set(getPosicion().x,getPosicion().y,textura.getWidth(),textura.getHeight());
        }

    }

    public void moverArriba(){
        if( getPosicion().y<=Gdx.graphics.getHeight()){
            getPosicion().y = getPosicion().y+getVelocidad();
            rect.set(getPosicion().x,getPosicion().y,textura.getWidth(),textura.getHeight());
        }

    }
    public void moverAbajo(){
        if(getPosicion().y>=0){
            getPosicion().y = getPosicion().y-getVelocidad();
            rect.set(getPosicion().x,getPosicion().y,textura.getWidth(),textura.getHeight());
        }

    }
    public void quitarVida(){
        int vida = this.getVidas()-1;
        setVidas(vida);

    }
    public void aumentarVelocidad(){
        setVelocidad(this.getVelocidad()+1);
    }


    public Vector2 getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    public int getPosiTextura() {
        return posiTextura;
    }

    public void setPosiTextura(int posiTextura) {
        this.posiTextura = posiTextura;
    }
}
