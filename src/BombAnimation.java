import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxgl.time.LocalTimer;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class BombAnimation extends Component {

    private PhysicsComponent physics = new PhysicsComponent();
    private LocalTimer hopDelay;
    private Texture bombTexture;


    @Override
    public void onUpdate(double tpf) {
        if (hopDelay.elapsed(Duration.seconds(2.5))){
            hop();
            hopDelay.capture();
        }
    }

    @Override
    public void onAdded() {
        Image image = new Image("assets/textures/bomb.png",50,50,true,true);
        bombTexture = new Texture(image);

        entity.setViewWithBBox(bombTexture);
        hopDelay = FXGL.newLocalTimer();
        hopDelay.capture();
    }

    public void hop(){
        physics.setVelocityY(-400);
    }
}
