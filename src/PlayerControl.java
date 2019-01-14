import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerControl extends Component {

    private PhysicsComponent physics;

    @Override
    public void onUpdate(double tpf) {

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