package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Nave extends Personaje{

    private TipoDisparo disparo;



    private boolean inmune;


    public Nave(Vector2 posicion, Texture textura, int vidas, int velocidad) {
        super(posicion, textura, vidas, velocidad);

    }
    public void meMori(){

    }

    public enum TipoDisparo{
        UNO,RAFAGA
    }
    public boolean isInmune() {
        return inmune;
    }

    public void setInmune(boolean inmune) {
        this.inmune = inmune;
    }


}
