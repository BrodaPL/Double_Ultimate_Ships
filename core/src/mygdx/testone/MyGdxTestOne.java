package mygdx.testone;

import assets.images.ImagesResources;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxTestOne extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img,img2;
	ImagesResources imagesRes = new ImagesResources();
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		img = new Texture(imagesRes.badlogicJPG());
		img2 = new Texture(imagesRes.space.ships.spaceShuttle_1PNG());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img2,100,100);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
