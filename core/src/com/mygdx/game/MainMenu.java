package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {


    private Viewport viewport;
    private OrthographicCamera camera;

    TextureAtlas menuButtonsAtlas;
    TextureRegion button_down, button_right, button_up, button_left, button_plus, button_minus,
            button_X, fieldbutton_transparent, fieldbutton_flat;

    private boolean TEEEEEEEEEEEEEEEEEEEEEEEEEST;

    Skin skin;
    Stage stage;

    Button downButton,upButton,leftButton,rightButton,crossButton,plusButton,minusButton, soundMinus, soundPlus;
    TextButton startButton, optionsButton, exitButton, backButton, soundButton, resolutionButton;

    final Blore game;

    public MainMenu(final Blore game){
        this.game = game;
        Assets.load();

        Gdx.graphics.setWindowedMode(Assets.screenWidth,
                Assets.screenHeight);

        createAtlas();
        createSkin();

        setupStartMenu();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        viewport.update(Assets.screenWidth,
				Assets.screenHeight);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();

		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			setupStartMenu();
		}

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }

    public void setupOptionsMenu(){
        setupCameraAndViewport(Assets.screenWidth,
                Assets.screenHeight);
        setupStage();

        setupOpionsButtons();
        addOptionsActors();

        Gdx.input.setInputProcessor(stage);
    }

    public void setupOpionsButtons(){
        backButton = new TextButton("back", skin, "default");
        backButton.setWidth(200);
        backButton.setHeight(80);
        backButton.setTransform(true);
        backButton.setScale((float)Assets.screenWidth/1920);
        backButton.setPosition(Gdx.graphics.getWidth()/2 - ((backButton.getWidth() * backButton.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((backButton.getHeight() * backButton.getScaleY())/2) -
                        (Assets.screenHeight/10)*4);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                Assets.settings.putFloat("sound", Assets.sound);
                Assets.settings.putInteger("screenWidth", Assets.screenWidth);
                Assets.settings.putInteger("screenHeight", Assets.screenHeight);
                Assets.settings.flush();
                stage.dispose();
                setupStartMenu();
                super.clicked(event, x, y);
            }
        });

        soundButton = new TextButton(String.valueOf((int)(Assets.sound*10)), skin, "default");
        soundButton.setWidth(200);
        soundButton.setHeight(80);
        soundButton.setTransform(true);
        soundButton.setScale((float)Assets.screenWidth/1920);
        soundButton.setPosition(Gdx.graphics.getWidth()/2 - ((soundButton.getWidth() * soundButton.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((soundButton.getHeight() * soundButton.getScaleY())/2) -
                        (Assets.screenHeight/10));

        //position aligned to soundButton
        soundMinus = new Button(skin, "minus");
        soundMinus.setWidth(64);
        soundMinus.setHeight(64);
        soundMinus.setTransform(true);
        soundMinus.setScale((float)Assets.screenWidth/1920);
        soundMinus.setPosition(Gdx.graphics.getWidth()/2 - ((soundButton.getWidth() * soundButton.getScaleX())/2) -
                        (Assets.screenWidth/10)*0.5f -
                        ((soundMinus.getWidth() * soundMinus.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((soundMinus.getHeight() * soundMinus.getScaleY())/2) -
                        (Assets.screenHeight/10));
        soundMinus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(Assets.sound > 0) {
                    Assets.sound -= 0.1;
                    soundButton.setText(String.valueOf((int)(Assets.sound*10)));}
                super.clicked(event, x, y);
            }
        });

        //position aligned to soundButton
        soundPlus = new Button(skin, "plus");
        soundPlus.setWidth(64);
        soundPlus.setHeight(64);
        soundPlus.setTransform(true);
        soundPlus.setScale((float)Assets.screenWidth/1920);
        soundPlus.setPosition(Gdx.graphics.getWidth()/2 + ((soundButton.getWidth() * soundButton.getScaleX())/2) +
                        (Assets.screenWidth/10)*0.5f -
                        ((soundPlus.getWidth() * soundPlus.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((soundPlus.getHeight() * soundPlus.getScaleY())/2) -
                        (Assets.screenHeight/10));
        soundPlus.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                if(Assets.sound < 1) {
                    Assets.sound += 0.1;
                    soundButton.setText(String.valueOf((int)(Assets.sound*10)));}
                super.clicked(event, x, y);
            }
        });

        resolutionButton = new TextButton(String.valueOf(Assets.screenWidth) + " x " +
                String.valueOf(Assets.screenHeight), skin, "default");
        resolutionButton.setWidth(200);
        resolutionButton.setHeight(80);
        resolutionButton.setTransform(true);
        resolutionButton.setScale((float)Assets.screenWidth/1920);
        resolutionButton.setPosition(Gdx.graphics.getWidth()/2 -
                        ((resolutionButton.getWidth() * resolutionButton.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((resolutionButton.getHeight() * resolutionButton.getScaleY())/2) +
                        Assets.screenHeight/10);
        resolutionButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(Assets.screenWidth == 1280){
                    Assets.screenWidth = 1920;
                    Assets.screenHeight = 1080;
                    Assets.settings.putInteger("screenWidth", 1920);
                    Assets.settings.putInteger("screenHeight",1080);
                }else {
                    Assets.screenWidth = 1280;
                    Assets.screenHeight = 720;
                    Assets.settings.putInteger("screenWidth", 1280);
                    Assets.settings.putInteger("screenHeight",720);
                }
                Assets.settings.putInteger("screenWidth", Assets.screenWidth);
                Assets.settings.putInteger("screenHeight", Assets.screenHeight);


                Gdx.graphics.setWindowedMode(Assets.screenWidth,
                        Assets.screenHeight);
                resolutionButton.setText(String.valueOf(Assets.screenWidth) + " x " +
                        String.valueOf(Assets.screenHeight));

                stage.dispose();
                setupOptionsMenu();
                super.clicked(event, x, y);
            }
        });

    }

    public void addOptionsActors(){
        stage.addActor(backButton);
        stage.addActor(soundButton);
        stage.addActor(soundMinus);
        stage.addActor(soundPlus);
        stage.addActor(resolutionButton);
    }

    public void setupStartMenu(){
        setupCameraAndViewport(Assets.screenWidth,
                Assets.screenHeight);
        setupStage();

        setupStartButtons();
        addStartActors();

        Gdx.input.setInputProcessor(stage);
    }

    public void setupStartButtons(){
        startButton = new TextButton("Start", skin, "default");
        startButton.setWidth(200);
        startButton.setHeight(80);
        startButton.setTransform(true);
        startButton.setScale((float)Assets.screenWidth/1920);
        startButton.setPosition(Gdx.graphics.getWidth()/2 - ((startButton.getWidth() * startButton.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((startButton.getHeight() * startButton.getScaleY())/2) +
                        Assets.screenHeight/10);
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                super.clicked(event, x, y);
            }
        });

        optionsButton = new TextButton("Options", skin, "default");
        optionsButton.setWidth(200);
        optionsButton.setHeight(80);
        optionsButton.setTransform(true);
        optionsButton.setScale((float)Assets.screenWidth/1920);
        optionsButton.setPosition(Gdx.graphics.getWidth()/2 - ((optionsButton.getWidth() *
                        optionsButton.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((optionsButton.getHeight() * optionsButton.getScaleY())/2));
        optionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                stage.dispose();
                setupOptionsMenu();

                super.clicked(event, x, y);
            }
        });


        exitButton = new TextButton("Exit", skin, "default");
        exitButton.setWidth(200);
        exitButton.setHeight(80);
        exitButton.setTransform(true);
        exitButton.setScale((float)Assets.screenWidth/1920);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 - ((exitButton.getWidth() * exitButton.getScaleX())/2),
                Gdx.graphics.getHeight()/2 - ((exitButton.getHeight() * exitButton.getScaleY())/2) -
                        Assets.screenHeight/10);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //Exits the game
                stage.dispose();
                //Gdx.app.exit();
                super.clicked(event, x, y);
            }
        });
    }

    public void setupCameraAndViewport(int x, int y){
        camera = new OrthographicCamera();
        viewport = new FitViewport(x, y, camera);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    public void setupStage(){
        stage = new Stage(viewport);
    }

    public void addStartActors(){
        //adding to stage
        stage.addActor(startButton);
        stage.addActor(optionsButton);
        stage.addActor(exitButton);
    }

    public void createAtlas(){
        //creating atlas and assigning buttons
        menuButtonsAtlas = Assets.menuButtons;
        button_down = menuButtonsAtlas.findRegion("down-arrow_menubutton");
        button_up = menuButtonsAtlas.findRegion("up-arrow_menubutton");
        button_left = menuButtonsAtlas.findRegion("left-arrow_menubutton");
        button_right = menuButtonsAtlas.findRegion("right-arrow_menubutton");
        button_plus = menuButtonsAtlas.findRegion("+_menubutton");
        button_minus = menuButtonsAtlas.findRegion("-_menubutton");
        button_X = menuButtonsAtlas.findRegion("X_menubutton");
        fieldbutton_flat = menuButtonsAtlas.findRegion("square_flat_menuslider");
        fieldbutton_transparent = menuButtonsAtlas.findRegion("square_menuslider");
    }

    public void createSkin(){
        //creating skin, assigning fonts and regions
        skin = new Skin(Gdx.files.internal("menuButtons.json"));
        skin.add("Assets.asgard", Assets.asgard);
        skin.addRegions(menuButtonsAtlas);
    }

}
