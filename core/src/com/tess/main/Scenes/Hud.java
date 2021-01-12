package com.tess.main.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tess.main.PillowFight;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewPort;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;
    private Integer swords;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label  pillowLabel;
    Label swordLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 300;
        timeCount = 0;
        score = 0;
        swords = 0;

        viewPort = new FitViewport(PillowFight.V_WIDTH, PillowFight.V_HEIGHT, new OrthographicCamera()) {
        };
        stage = new Stage(viewPort, sb);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("POKEY OAKS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pillowLabel = new Label("PILLOW GUY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        swordLabel = new Label("SWORDS", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(pillowLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(swordLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
