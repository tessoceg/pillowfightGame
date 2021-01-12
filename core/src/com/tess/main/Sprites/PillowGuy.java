package com.tess.main.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.tess.main.PillowFight;
import com.tess.main.Screens.PlayScreen;

public class PillowGuy extends Sprite {
    public World world;
    public Body b2body;
    private TextureRegion pillowGuyStand;

    public PillowGuy (World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("pillowGuyBig"));
        this.world = world;
        definePillowGuy();
        pillowGuyStand = new TextureRegion(getTexture(), 0, 0, 45, 45);
        setBounds(0, 0, 45, 45);
        setRegion(pillowGuyStand);
    }
    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y - getHeight()/2);
    }
    public void definePillowGuy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32, 32);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}
