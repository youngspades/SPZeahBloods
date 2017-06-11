package scripts.SPZeahBloods.tasks;

/**
 * Created by Adar on 6/10/17.
 */

import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.util.Task;

public class LookAtOtherBlock implements Task {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean validate() {
        return (Animations.isMining());
    }

    @Override
    public void execute() {
        RSObject[] objects = Objects.findNearest(40, ObjectNames.DENSE_RUNE_STONE_NAME);

        // If the other essence block can be chipped, look at it.
        if (objects.length == 2) {
            RSTile tile = objects[1].getPosition();
            Camera.turnToTile(tile);
        }
    }

    @Override
    public String toString() {
        return "Look At Other Block";
    }
}
