package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Fondo {
    Texture fondo;
    float x;
    float y;
    TextureRegion region;
    float rotacion;


    public Fondo(Texture fondo,float x, float y,float rotacion) {
        this.fondo = fondo;
        this.x = x;
        this.y = y;
        this.rotacion=rotacion;
        region = new TextureRegion(fondo,0,0,fondo.getWidth()-50,fondo.getHeight()-50);
    }

    public void pintaFondo(SpriteBatch batch){
        batch.draw(fondo,x,y);
    }
    public void pintaFondoRotando(SpriteBatch batch){
        batch.draw(region,0,0, (float) (fondo.getWidth()/2.5),fondo.getHeight()/2,fondo.getWidth(),fondo.getHeight(),Float.valueOf(String.valueOf(1.8)),Float.valueOf(String.valueOf(1.8)),rotacion);
    }

    public float getRotacion() {
        return rotacion;
    }

    public void setRotacion(float rotacion) {
        this.rotacion = rotacion;
    }

    public Texture getFondo() {
        return fondo;
    }

    public void setFondo(Texture fondo) {
        this.fondo = fondo;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
