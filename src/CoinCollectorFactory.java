import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class CoinCollectorFactory implements EntityFactory {

    @Spawns("platform")
    public Entity newPlatform(SpawnData data){
        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get
                        ("width"), data.<Integer>get("hight"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data){
        return Entities.builder()
                .from(data)
                .viewFromNodeWithBBox(new Circle(data.<Integer>get("width")/2, Color.GOLD))
                .build();
    }

}
