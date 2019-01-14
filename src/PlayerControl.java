import com.almasb.fxgl.entity.Control;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerControl extends Control {

    private PhysicsComponent physics;

    @Override
    public void onUpdate(Entity entity, double v) {

    }

    public void venstre(){
        physics.setVelocityX(-150);
    }

    public void hoejre(){
        physics.setVelocityX(150);
    }

    public void hop(){
        physics.setVelocityY(-200);

    }
}
