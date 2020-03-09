package com.mygdx.game.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Animaciones {
    private ArrayList<TextureRegion> aniNave;
    private ArrayList<Texture> naveTexturas;
    private ArrayList<Texture> marciTexturas;
    private ArrayList<Texture> rocaTexturas;
    private ArrayList<Texture> exploTexturas;
    private ArrayList<TextureRegion> exploAnimacion;
    private ArrayList<Texture> arraySCIFI;
    private Texture muerteBoss;
    private Texture gameOver;
    private Texture paktc;

    public Animaciones() {

        naveTexturas=new ArrayList<>();
        marciTexturas=new ArrayList<>();
        aniNave = new ArrayList<>();
        rocaTexturas = new ArrayList<>();
        exploTexturas= new ArrayList<>();
        exploAnimacion = new ArrayList<>();
        arraySCIFI = new ArrayList<>();
        for(int i=1;i<5;i++){
            Texture texNave = new Texture("ship/f"+String.valueOf(i)+".png");
            naveTexturas.add(texNave);
            aniNave.add(new TextureRegion(texNave,0,0,texNave.getWidth(),texNave.getHeight()));
        }
        for(int i=1;i<7;i++){
            Texture texMarcianos = new Texture("enemy/e_f"+String.valueOf(i)+".png");
            marciTexturas.add(texMarcianos);
        }
        for(int i=1;i<6;i++){
            Texture texRocas = new Texture("enemy/stone"+String.valueOf(i)+".png");
            rocaTexturas.add(texRocas);
        }
        String cad1="000";
        String cad2="00";
        Texture texPlo;
        for(int i=0;i<51;i++){
            if(i<10){
                texPlo = new Texture("explosion/explosion"+cad1+String.valueOf(i)+".png");
            }else{
                texPlo = new Texture("explosion/explosion"+cad2+String.valueOf(i)+".png");
            }
            exploTexturas.add(texPlo);
            exploAnimacion.add(new TextureRegion(texPlo,0,0,texPlo.getWidth(),texPlo.getHeight()));
        }
        Texture exploSCIFI;
        for(int i=1; i<=15;i++){
            if(i<10){
                exploSCIFI= new Texture("explosion4/k2_"+cad1+String.valueOf(i)+".png");
            }else{
                exploSCIFI= new Texture("explosion4/k2_"+cad2+String.valueOf(i)+".png");
            }
            arraySCIFI.add(exploSCIFI);

        }
        muerteBoss = new Texture("enemy/voxBoss.png");
        gameOver = new Texture("backgrounds/gameover (2).png");
        paktc = new Texture("backgrounds/petc.png");
    }

    public ArrayList<Texture> getArraySCIFI() {
        return arraySCIFI;
    }

    public void setArraySCIFI(ArrayList<Texture> arraySCIFI) {
        this.arraySCIFI = arraySCIFI;
    }

    public Texture getPaktc() {
        return paktc;
    }

    public void setPaktc(Texture paktc) {
        this.paktc = paktc;
    }

    public Texture getGameOver() {
        return gameOver;
    }

    public void setGameOver(Texture gameOver) {
        this.gameOver = gameOver;
    }

    public Texture getMuerteBoss() {
        return muerteBoss;
    }

    public void setMuerteBoss(Texture muerteBoss) {
        this.muerteBoss = muerteBoss;
    }

    public ArrayList<TextureRegion> getAniNave() {
        return aniNave;
    }

    public void setAniNave(ArrayList<TextureRegion> aniNave) {
        this.aniNave = aniNave;
    }

    public ArrayList<Texture> getNaveTexturas() {
        return naveTexturas;
    }

    public void setNaveTexturas(ArrayList<Texture> naveTexturas) {
        this.naveTexturas = naveTexturas;
    }

    public ArrayList<Texture> getMarciTexturas() {
        return marciTexturas;
    }

    public void setMarciTexturas(ArrayList<Texture> marciTexturas) {
        this.marciTexturas = marciTexturas;
    }

    public ArrayList<Texture> getRocaTexturas() {
        return rocaTexturas;
    }

    public void setRocaTexturas(ArrayList<Texture> rocaTexturas) {
        this.rocaTexturas = rocaTexturas;
    }

    public ArrayList<Texture> getExploTexturas() {
        return exploTexturas;
    }

    public void setExploTexturas(ArrayList<Texture> exploTexturas) {
        this.exploTexturas = exploTexturas;
    }

    public ArrayList<TextureRegion> getExploAnimacion() {
        return exploAnimacion;
    }

    public void setExploAnimacion(ArrayList<TextureRegion> exploAnimacion) {
        this.exploAnimacion = exploAnimacion;
    }
}
