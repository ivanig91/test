package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bala extends Personaje {
    boolean leDi= false;
    boolean afecta=true;
    public Bala(Vector2 posicion, Texture textura, int vidas, int velocidad) {
        super(posicion, textura, vidas, velocidad);
    }



    public void setLeDi(boolean leDi) {
        this.leDi = leDi;
    }

    public boolean isAfecta() {
        return afecta;
    }

    public void setAfecta(boolean afecta) {
        this.afecta = afecta;
    }
}