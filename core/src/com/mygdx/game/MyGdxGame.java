package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.model.Bala;
import com.mygdx.game.model.Enemigo;
import com.mygdx.game.model.Nave;
import com.mygdx.game.model.Roca;
import com.mygdx.game.screens.PantallaMenuPrincipal;
import com.mygdx.game.util.Constantes;

import java.util.Random;

public class MyGdxGame extends Game {



	@Override
	public void create () {
		((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrincipal());

	}

	@Override
	public void render () {
		super.render();

	}


	@Override
	public void dispose () {


	}
}
