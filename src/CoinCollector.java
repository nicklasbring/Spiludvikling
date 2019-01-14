import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.input.UserAction;
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


    @Override
    protected void initInput() {

        getInput().addAction(new UserAction("hoejre") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).hoejre();
            }
        }, KeyCode.D);


        getInput().addAction(new UserAction("venstre") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).venstre();
            }
        }, KeyCode.A);


        getInput().addAction(new UserAction("hop") {
            @Override
            protected void onAction() {
                player.getControl(PlayerControl.class).hop();
            }
        }, KeyCode.W);
    }


    @Override
    protected void initGame() {

        getGameWorld().add(new CoinCollectorFactory());
       getGameWorld().setLevelFromMap("coincollector.json");

        player = getGameWorld().spawn("player", 50, 50);

    }


        public static void main(String[]args){
            launch(args);
        }
    }


