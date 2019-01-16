import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;



public class CoinCollector extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setWidth(28 * 70);
        gameSettings.setHeight(15 * 70);
        gameSettings.setTitle("CoinCollector");
    }

    private Entity player;

    private boolean jumpActive = false;

    @Override
    protected void initInput() {

        getInput().addAction(new UserAction("hoejre") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).hoejre();
            }
        }, KeyCode.RIGHT);


        getInput().addAction(new UserAction("venstre") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).venstre();
            }
        }, KeyCode.LEFT);


        getInput().addAction(new UserAction("hop") {
            @Override
            protected void onAction() {
            }


            @Override
            protected void onActionBegin() {
                super.onActionBegin();
                if(!isJumpActive()) {
                    player.getComponent(PlayerControl.class).hop();
                    getAudioPlayer().playSound("jumpsound.wav");
                    setJumpActive(true);
                }
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();

            }
        }, KeyCode.SPACE);
    }


    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new CoinCollectorFactory());
        getGameWorld().setLevelFromMap("coincollector.json");

        player = getGameWorld().spawn("player", 50, 950);

        //Image baggrund = new Image("assets/textures/baggrund.jpg", 1920, 1080, false, false);
        //getGameScene().setBackgroundRepeat(baggrund);  --> Forstår ikke hvorfor dette ikke virker. Det gjorde det forleden
    }

    public int coincounter = 0;

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(CoinCollectorType.PLAYER, CoinCollectorType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                getAudioPlayer().playSound("coinsound.mp3");
                coincounter++;
                coin.removeFromWorld();
                System.out.println(coincounter);
            }
        });


        getPhysicsWorld().addCollisionHandler(new CollisionHandler(CoinCollectorType.PLAYER, CoinCollectorType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                getAudioPlayer().playSound("winnersound.wav");
                getDisplay().showMessageBox("Tillykke du klarede første level! \nDu fik i alt " + coincounter + " ud af 20");
                System.out.println("Level completed");

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(CoinCollectorType.PLAYER, CoinCollectorType.PLATFORM) {
            @Override
            protected void onCollisionBegin(Entity player, Entity platform) {
                setJumpActive(false);

            }
        });
    }


    public boolean isJumpActive() {
        return jumpActive;
    }

    public void setJumpActive(boolean jumpActive) {
        this.jumpActive = jumpActive;
    }

    public static void main(String[]args){
        launch(args);
    }
}
