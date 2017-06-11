
/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.actions;

import org.tribot.api.General;
import org.tribot.api.DynamicClicking;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.constants.Positions;
import scripts.SPZeahBloods.actions.AFK;
import scripts.SPZeahBloods.util.ACamera;

public class Mining {
    public static final boolean isMining() {
        int anim = Player.getAnimation();
        return (anim == Animations.MINE_STANDING_ANIM || anim == Animations.MINE_CROUCHED_ANIM);
    }

    public static final void handleMining(ACamera aCamera) {
        RSObject[] objects = Objects.findNearest(40, ObjectNames.DENSE_RUNE_STONE_NAME);
        Camera.setRotationMethod(Camera.ROTATION_METHOD.ONLY_KEYS);

        if (isMining()|| Player.isMoving()) {

            // If the other essence block can be chipped, look at it.
            if (objects.length == 2) {
                RSTile tile = objects[1].getPosition();
                aCamera.turnToTile(tile);
            }

            if (isMining()) {
                Mouse.leaveGame(true);
            }
        } else {
            // If essence can be chipped
            if (objects.length > 0) {
                RSTile tile = objects[0].getPosition();
                aCamera.turnToTile(tile);
                if (objects[0].isOnScreen()) {
                    DynamicClicking.clickRSObject(objects[0], "Chip");

                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100); // Add this in to reduce CPU usage
                            return (isMining() || Player.isMoving());
                        }
                    }, General.random(1350, 2350));
                }
            } else {
                WebWalking.walkTo(Positions.getMineArea().getRandomTile());
            }
        }
    }
}
