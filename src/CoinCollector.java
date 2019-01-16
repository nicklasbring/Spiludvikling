import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.entity.Entity;
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
                    setJumpActive(true);
                }
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();

            }
        }, KeyCode.SPACE);


    /*
        getInput().addAction(new UserAction("forkert tast") {
        @Override
        protected void onActionBegin(){
            getAudioPlayer().playSound("Error-tone.mp3");
        }
    }, KeyCode.SPACE);
    */
}


    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new CoinCollectorFactory());
        getGameWorld().setLevelFromMap("coincollector.json");

        player = getGameWorld().spawn("player", 50, 50);

        //getGameScene().setBackgroundRepeat("baggrund.jpg");

    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(CoinCollectorType.PLAYER, CoinCollectorType.COIN) {
            @Override
            protected void onCollisionBegin(Entity player, Entity coin) {
                coin.removeFromWorld();
            }
        });


        getPhysicsWorld().addCollisionHandler(new CollisionHandler(CoinCollectorType.PLAYER, CoinCollectorType.DOOR) {
            @Override
            protected void onCollisionBegin(Entity player, Entity door) {
                getDisplay().showMessageBox("Congratulation, you made it!");
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
