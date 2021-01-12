package com.tess.main.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.tess.main.Controller;
import com.tess.main.PillowFight;
import com.tess.main.Scenes.Hud;
import com.tess.main.Sprites.PillowGuy;
import com.tess.main.Tools.B2WorldCreator;

public class PlayScreen implements Screen {

    private PillowFight game;
    private TextureAtlas atlas;
    //view stuff
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    //tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    //box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    //sprite created
    private PillowGuy player;
    Controller controller;


    public PlayScreen(PillowFight game) {
        atlas = new TextureAtlas("PillowGuy2.pack");
        this.game = game;
        gameCam = new OrthographicCamera();
        //fitviewport maintains virtual aspect ration for any screen  size
        gamePort = new FitViewport(PillowFight.V_WIDTH, PillowFight.V_HEIGHT, gameCam);
        //create our game hud for scores and time info
        hud = new Hud(game.batch);
        maploader = new TmxMapLoader();
        map = maploader.load("pillowfightmap9.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        //initially set our gamecam to be centered correctly at the start
        gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
        //creates physics for world
        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        //on screen controlls
        controller = new Controller(game);

        //create and add walls
        new B2WorldCreator(world, map);

        //creates pillowguy in game world
        player = new PillowGuy(world, this);

    }
    public TextureAtlas getAtlas() {
        return atlas;
    }

    @Override
    public void show() {

    }
    public void handleInput (float dt) {
        if(controller.isRightPressed()) {
            player.b2body.setLinearVelocity(new Vector2(125, player.b2body.getLinearVelocity().y));
        } else if (controller.isLeftPressed()) {
            player.b2body.setLinearVelocity(new Vector2(-125, player.b2body.getLinearVelocity().y));
        } else if (controller.isUpPressed()){
            player.b2body.setLinearVelocity(new Vector2(player.b2body.getLinearVelocity().x, 125));
        } else if (controller.isDownPressed()) {
            player.b2body.setLinearVelocity(new Vector2(player.b2body.getLinearVelocity().x, -125));
        }

    }
    public void update (float dt) {
        //handle user input first
        handleInput(dt);

        world.step(1/60f, 6, 2);

        player.update(dt);

        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.position.y = player.b2body.getPosition().y;
        //update our gamecam with the corrent coordinates after changes
        gameCam.update();
        //tells the renderer to only draw what the gamecam sees in the world
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);
        //clear the game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //render the game map
        renderer.render();
        //renderer the Box2DDebuglines
         b2dr.render(world, gameCam.combined);

         game.batch.setProjectionMatrix(gameCam.combined);
         game.batch.begin();
         player.draw(game.batch);
         game.batch.end();
        //set batch to draw what the hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        controller.draw();
//        game.batch.begin();
//        game.batch.draw(texture, 0, 0);
//        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        controller.resize(width, height);
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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();


    }
}
