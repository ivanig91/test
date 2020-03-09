package com.mygdx.game.model;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Roca extends  Personaje {
    TextureRegion region;
    float rotacion;
    public Roca(Vector2 posicion, Texture textura, int vidas, int velocidad) {
        super(posicion, textura, vidas, velocidad);
        region = new TextureRegion(textura,0,0,textura.getWidth(),textura.getHeight());
    }


    public float getRotacion() {
        return rotacion;
    }

    public void setRotacion(float rotacion) {
        this.rotacion = rotacion;
    }
}
