
/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.tasks;

import org.tribot.api.General;
import org.tribot.api.DynamicClicking;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.SPZeahBloods;
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.constants.Positions;
import scripts.SPZeahBloods.actions.AFK;
import scripts.SPZeahBloods.util.Task;

public class MineBlock implements Task {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean validate() {
        return (!Animations.isMining() && !Player.isMoving() && !Inventory.isFull());
    }

    @Override
    public void execute() {
        RSObject[] objects = Objects.findNearest(40, ObjectNames.DENSE_RUNE_STONE_NAME);

        // If essence can be chipped
        if (objects.length > 0) {
            General.println("essence can be chipped");
            RSTile tile = objects[0].getPosition();
            SPZeahBloods.aCamera.turnToTile(tile);
            if (objects[0].isOnScreen()) {
                if (DynamicClicking.clickRSObject(objects[0], "Chip")) {
                    General.println("essence on screen");
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100); // Add this in to reduce CPU usage
                            return (Animations.isMining() || Player.isMoving());
                        }
                    }, General.random(1350, 2350));
                } else {
                    WebWalking.walkTo(Positions.getMineArea().getRandomTile());
                }
            }
        }
    }
}
