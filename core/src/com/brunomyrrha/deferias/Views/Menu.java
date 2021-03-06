package com.brunomyrrha.deferias.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.brunomyrrha.deferias.Controllers.Animated;
import com.brunomyrrha.deferias.Controllers.GameStateManager;
import com.brunomyrrha.deferias.Controllers.LoadManager;
import com.brunomyrrha.deferias.Controllers.State;

/**
 * Created by brunomyrrha on 12/07/17.
 */

public class Menu extends State {
    //Staging and Rendering fonts
    public static final int WIDTH = 800;
    public static final int HEIGHT = 1280;

    private Table table, tableProgram;
    private Stage stage;
    private Viewport viewport;

    private Image btnCulture,btnEducation, btnHelp, btnProgram;

    private Texture background,sesc;
    private Animated lion;

    public Menu(final GameStateManager gsm, final LoadManager lm){
        super(gsm,lm);
        cam.setToOrtho(false,WIDTH,HEIGHT);
        viewport = new FitViewport(WIDTH,HEIGHT);
        table = new Table();
        tableProgram = new Table();
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        //Image loads
        background = lm.getTexture("bg.png");
        sesc = lm.getTexture("sesc.png");
        lion = new Animated("lion",.06f,lm);

        btnHelp = new Image(lm.getTexture("btnHelp.png"));
        btnCulture = new Image (lm.getTexture("btnCulture.png"));
        btnEducation = new Image(lm.getTexture("btnEducation.png"));
        btnProgram = new Image(lm.getTexture("btnProgram.png"));

        //Listerners

        btnProgram.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.net.openURI("http://bit.ly/2uDrhPJ");
            }
        });

        btnHelp.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new Help(gsm,lm));
            }
        });

        btnCulture.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new Culture(gsm,lm));
            }
        });

        btnEducation.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                gsm.set(new Education(gsm,lm));
            }
        });

        //Table Adds
        table.add(btnEducation).size(btnEducation.getWidth(),btnEducation.getHeight()).pad(10);
        table.add(btnCulture).size(btnCulture.getWidth(),btnCulture.getHeight()).pad(10);
        table.add(btnHelp).size(btnHelp.getWidth(), btnHelp.getHeight()).pad(10);

        tableProgram.add(btnProgram).size(btnProgram.getWidth(),btnProgram.getHeight());
        tableProgram.setFillParent(true);
        tableProgram.bottom().padBottom(110);

        table.setFillParent(true);
        table.top().padTop(360);

        //Table loads
        stage.addActor(table);
        stage.addActor(tableProgram);
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float deltaTime) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,0);
        lion.draw();
        sb.draw(lion.textureRegion(),150,170);
        sb.draw(sesc,0,0);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
