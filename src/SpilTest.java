import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.security.Key;
import java.util.Map;

public class SpilTest extends GameApplication {

        @Override
        protected void initSettings(GameSettings gameSettings) {
            gameSettings.setWidth(500);
            gameSettings.setHeight(500);
            gameSettings.setTitle("Spiltest");
        }

        private Entity player;

        @Override
        protected void initGame() {
            player = Entities.builder()
                .at(250, 250)
                //.viewFromNode(new Rectangle(25, 25, Color.GREEN)) -- Since we don't need a rectangle anymore
                .viewFromTexture("narwhal.png")
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
        Texture narwhalTexture = getAssetLoader().loadTexture("narwhal.png");

        narwhalTexture.setTranslateX(50);
        narwhalTexture.setTranslateY(250);

        Text textPixels= new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);

        textPixels.textProperty().bind(getGameState().intProperty("rykketPixels").asString());

        getGameScene().addUINode(textPixels);
        getGameScene().addUINode(narwhalTexture);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("rykketPixels", 0);
    }

    public static void main(String[] args) {
            launch(args);
        }
    }

