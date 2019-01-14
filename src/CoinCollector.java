import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.Map;

public class CoinCollector extends GameApplication {

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(28 * 70);
        gameSettings.setHeight(15 * 70);
        gameSettings.setTitle("CoinCollector");
    }


    private Entity player;

        @Override
        protected void initGame() {
            //getGameWorld().setLevelFromMap("Januaropgave.json");    //Dette er metoden man skal kalde for at hente den bane jeg har lavet i Tiled
            player = Entities.builder()
                .at(250, 250)
                .viewFromTexture("Racecar.png") //Bare fra det gamle spil. Den bliver selvfølgelig lavet om til en anden! :)
                .buildAndAttach(getGameWorld());
        }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Højre") {
            @Override
            protected void onAction() {
                player.translateX(5); //Rykker 5 pixels til højre
                getGameState().increment("rykketPixels", +5);
            }

        }, KeyCode.D);

        input.addAction(new UserAction("Venstre") {
            @Override
            protected void onAction() {
                player.translateX(-5); //Rykker 5 pixels til venstre
                getGameState().increment("rykketPixels", +5);
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Op") {
            @Override
            protected void onAction() {
                player.translateY(-5); //Rykker 5 pixels op
                getGameState().increment("rykketPixels", +5);
            }
        }, KeyCode.W);

        input.addAction(new UserAction("Ned") {
            @Override
            protected void onAction() {
                player.translateY(5); //Rykker 5 pixels ned
                getGameState().increment("rykketPixels", +5);
            }
        }, KeyCode.S);

        input.addAction(new UserAction("Forkert tast") {
            @Override
            protected void onActionBegin(){
                getAudioPlayer().playSound("Error-tone.mp3");
            }
            }, KeyCode.F);
    }


    @Override
    protected void initUI() {
        Text textPixels= new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);

        textPixels.textProperty().bind(getGameState().intProperty("rykketPixels").asString());

        getGameScene().addUINode(textPixels);
        getGameScene().setBackgroundRepeat("Racetrack1.png");
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("rykketPixels", 0);
    }

    public static void main(String[] args) {
            launch(args);
        }
    }

