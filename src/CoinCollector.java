import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.css.converter.CursorConverter;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import org.intellij.lang.annotations.JdkConstants;
import org.jetbrains.annotations.NotNull;

import java.awt.*;


public class CoinCollector extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {

        gameSettings.setWidth(28 * 70);
        gameSettings.setHeight(15 * 70);
        gameSettings.setTitle("CoinCollector");
    }

    private Entity player;


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
                player.getComponent(PlayerControl.class).hop();
            }
        }, KeyCode.SPACE);
    }


    @Override
    protected void initGame() {

        getGameWorld().addEntityFactory(new CoinCollectorFactory());
        getGameWorld().setLevelFromMap("coincollector6.json");

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
    }

    public static void main(String[]args){
        launch(args);
    }
}
