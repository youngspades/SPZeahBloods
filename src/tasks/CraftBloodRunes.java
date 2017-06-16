
/**
 * Created by Adar on 6/16/17.
 */

package scripts.SPZeahBloods.src.tasks;

import org.tribot.api.General;
import org.tribot.api.DynamicClicking;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.src.SPZeahBloods;
import scripts.SPZeahBloods.src.constants.Animations;
import scripts.SPZeahBloods.src.constants.ItemIds;
import scripts.SPZeahBloods.src.constants.ObjectNames;
import scripts.SPZeahBloods.src.constants.Positions;
import scripts.SPZeahBloods.src.util.Task;

public class CraftBloodRunes implements Task {

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean validate() {
        boolean hasFragments = Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) >= 1;
        boolean hasCorrectDenseAmount = ((Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) == 25 && Inventory.getCount(ItemIds.BLOOD_RUNES_ID) >= 1)
                    || (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) == 26 && Inventory.getCount(ItemIds.BLOOD_RUNES_ID) == 0)
                    || (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) == 0));

        return (hasFragments && hasCorrectDenseAmount
                && Positions.atBloodAltar()
        );
    }

    @Override
    public void execute() {
        RSObject[] objects = Objects.findNearest(40, ObjectNames.BLOOD_ALTAR_NAME);

        // If altar exists
        if (objects.length > 0) {
            RSTile tile = objects[0].getPosition();
            if (objects[0].isOnScreen()) {
                if (DynamicClicking.clickRSObject(objects[0], "Bind")) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100); // Add this in to reduce CPU usage
                            return (Player.getAnimation() == Animations.VENERATE_ANIM || Player.isMoving());
                        }
                    }, General.random(1350, 2350));
                }
            } else {
                SPZeahBloods.aCamera.turnToTile(tile);
            }
        }
    }
}
