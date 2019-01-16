import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.util.Duration;

public class PlayerControl extends Component {

    private PhysicsComponent physics;
    private AnimatedTexture texture;
    private AnimationChannel idle, walk;

    public PlayerControl(){
        idle = new AnimationChannel("runningman.png", 8, 26, 47, Duration.seconds(1), 1, 1);
        walk = new AnimationChannel("runningman.png", 8, 26, 47, Duration.seconds(1), 0, 7);

        texture = new AnimatedTexture(idle);
    }

    @Override
    public void onAdded(){
        entity.setView(texture);
    }

    @Override
    public void onUpdate(double tpf) {

        if(physics.getVelocityX()>0){
            if(texture.getAnimationChannel() == idle){
                texture.loopAnimationChannel(walk);
            }

            if(FXGLMath.abs(physics.getVelocityX())<50){
                physics.setVelocityX(0);
                texture.loopAnimationChannel(idle);
            }

        }


        if(physics.getVelocityX()<0){
            if(texture.getAnimationChannel() == idle){
                texture.loopAnimationChannel(walk);
            }

            if(FXGLMath.abs(physics.getVelocityX())<50){
                physics.setVelocityX(0);
                texture.loopAnimationChannel(idle);
            }
        }


    }

    public void venstre(){
        physics.setVelocityX(-150);
        entity.setScaleX(-1);
    }

    public void hoejre(){
        physics.setVelocityX(150);
        entity.setScaleX(1);
    }

    public void hop(){
        physics.setVelocityY(-200);
    }


}