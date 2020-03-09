package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PantallaPause implements Screen {
    Stage stage;
    boolean normal=true;
    String playString;
    String confString;
    String salirString;
    int dificultad = 1;
    String difiString;
    VisTextButton playButton;
    VisTextButton quitButton;
    VisTextButton configButton;
    PantallaJuego elJuego;


    public PantallaPause(PantallaJuego elJuego) {
        this.elJuego = elJuego;
        dificultad=elJuego.getDificultad();
    }

    @Override
    public void show() {

        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        playString = "CONTINUE";
        confString= "NEW GAME";
        salirString = "QUIT";



        playButton = new VisTextButton(playString);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game) Gdx.app.getApplicationListener()).setScreen(elJuego);
                dispose();

            }
        });

        quitButton = new VisTextButton(salirString);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.exit(0);
                // Salir del juego
            }
        });
        configButton = new VisTextButton(confString);
        configButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Ir a la pantalla de configuración
                elJuego.getBackSong().stop();
                elJuego.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego(dificultad));
                dispose();


            }
        });

        VisLabel aboutLabel = new VisLabel("Demo libGDX\n(c) Iván Villarreal 2020");

        // Añade filas a la tabla y añade los componentes
        Drawable covid= new Drawable() {
            @Override
            public void draw(Batch batch, float x, float y, float width, float height) {
                batch.draw(new Texture("backgrounds/covid2.png"),x,y);
            }

            @Override
            public float getLeftWidth() {
                return 0;
            }

            @Override
            public void setLeftWidth(float leftWidth) {

            }

            @Override
            public float getRightWidth() {
                return 0;
            }

            @Override
            public void setRightWidth(float rightWidth) {

            }

            @Override
            public float getTopHeight() {
                return 0;
            }

            @Override
            public void setTopHeight(float topHeight) {

            }

            @Override
            public float getBottomHeight() {
                return 0;
            }

            @Override
            public void setBottomHeight(float bottomHeight) {

            }

            @Override
            public float getMinWidth() {
                return 0;
            }

            @Override
            public void setMinWidth(float minWidth) {

            }

            @Override
            public float getMinHeight() {
                return 0;
            }

            @Override
            public void setMinHeight(float minHeight) {

            }
        };

        VisImage covidImage = new VisImage(covid);
        table.row();
        table.add(covidImage).left().width(500).height(250).pad(5);
        table.row();
        table.add(playButton).center().width(200).height(100).pad(5);
        table.row();
        table.add(configButton).center().width(200).height(50).pad(5);
        table.row();
        table.add(quitButton).center().width(200).height(50).pad(5);
        table.row();
        table.add(aboutLabel).left().width(200).height(20).pad(5);

        Gdx.input.setInputProcessor(stage);
    }
    private void cambiarTextos(boolean normal){
        if(!normal){
            playButton.setText("PLAY");
            configButton.setText(difiString);
            quitButton.setText("QUIT");
        }else{
            playButton.setText("EASY");
            configButton.setText("MEDIUM");
            quitButton.setText("HARD");
        }
    }

    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Pinta la UI en la pantalla
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Redimensiona la escena al redimensionar la ventana del juego
        stage.getViewport().update(width, height);
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
        // Libera los recursos de la escena
        stage.dispose();
    }


}
