package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Boss extends Personaje {
    boolean poderActivado; //Este booleano me indicará si el poder del boss se usa o no
    boolean vivo;

    // para hacer que el enemigo me persiga resto mi posicion a la posicion del enemigo
    //si el resultado es negativo, muevo el boss a la izquierda, o hacia abajo

    public Boss(Vector2 posicion, Texture textura, int vidas, int velocidad) {
        super(posicion, textura, vidas, velocidad);
        this.poderActivado=false;
        this.vivo=false;
    }
    public Boss( Texture textura, int vidas, int velocidad) {
        super(textura, vidas, velocidad);
        this.poderActivado=false;
        this.vivo=false;
    }

    public boolean isPoderActivado() {
        return poderActivado;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void setPoderActivado(boolean poderActivado) {
        this.poderActivado = poderActivado;
    }
    public  void cambiaPoderActivado(){
        if(isPoderActivado()){
            setPoderActivado(false);
        }else{
            setPoderActivado(true);
        }
    }
}
