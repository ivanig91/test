package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Explosion {
    Texture textura;
    Vector2 posicion;
    boolean completo;
    int frameActual;

    public Explosion(Texture textura, Vector2 posicion) {
        this.textura = textura;
        this.posicion = posicion;
        this.completo = false;
        frameActual=0;

    }
    public void mePinto(SpriteBatch batch){
        batch.draw(textura,posicion.x-200,posicion.y-200);
    }

    public void sumarFrame(){
        frameActual++;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public Vector2 getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public int getFrameActual() {
        return frameActual;
    }

    public void setFrameActual(int frameActual) {
        this.frameActual = frameActual;
    }
}
