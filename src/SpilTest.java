import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;

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
                                .viewFromNode(new Rectangle(25, 25, Color.GREEN))
                                .buildAndAttach(getGameWorld());
            }


        @Override
        protected void initInput() {
            Input input = getInput();

            input.addAction(new UserAction("Højre"){
                @Override
                protected void onAcion(){
                    player.translateX(5); //Rykker 5 pixel til højre
                }
            }, KeyCode.D);
        }


    public static void main(String[] args) {
            launch(args);
        }
    }

