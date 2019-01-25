import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.shape.Rectangle;


public class CoinCollectorFactory implements EntityFactory {

    @Spawns("platform")
    public Entity newPlatform(SpawnData data){
        return Entities.builder()
                .type(CoinCollectorType.PLATFORM)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .with(new PhysicsComponent())
                .build();
    }


    @Spawns("bomb")
    public Entity newBomb(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(CoinCollectorType.ENEMY)
                .from(data)
                .with(new CollidableComponent(true))
                .with(physics)
                .with(new BombAnimation())
                .build();
    }


    @Spawns("player")
    public Entity newPlayer(SpawnData data){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        return Entities.builder()
                .type(CoinCollectorType.PLAYER)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(26, 47))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new PlayerControl())
                .build();
    }


    @Spawns("coin")
    public Entity newCoin(SpawnData data){
        return Entities.builder()
                .type(CoinCollectorType.COIN)
                .from(data)
                .viewFromNodeWithBBox(new Rectangle(35,35))
                .with(new CollidableComponent(true))
                .viewFromTexture("coin.png")
                .build();

    }


    @Spawns("door")
    public Entity newDoor(SpawnData data){
        return Entities.builder()
                .type(CoinCollectorType.DOOR)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }



    @Spawns("")
    public Entity newBlank(SpawnData data){
        return Entities.builder()
                .from(data)
                .build();
    }
}