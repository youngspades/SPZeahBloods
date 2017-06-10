
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
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.constants.Positions;
import scripts.SPZeahBloods.actions.AFK;

public class Mining {
    public static final boolean isMining() {
        int anim = Player.getAnimation();
        return (anim == Animations.MINE_STANDING_ANIM || anim == Animations.MINE_CROUCHED_ANIM);
    }

    public static final void handleMining() {
        RSObject[] objects = Objects.findNearest(40, ObjectNames.DENSE_RUNE_STONE_NAME);
        Camera.setRotationMethod(Camera.ROTATION_METHOD.ONLY_KEYS);

        if (isMining()|| Player.isMoving()) {

            if (objects.length == 2) {
                RSTile tile = objects[1].getPosition();
                Camera.turnToTile(tile);
            }

            Mouse.leaveGame(true);
        } else {
            if (AFK.shouldAFK()) {
                AFK.sleep();
            }

            if (objects.length > 0) {
                RSTile tile = objects[0].getPosition();
                General.sleep(150, 330);
                Camera.turnToTile(tile);
                General.sleep(135, 345);
                DynamicClicking.clickRSObject(objects[0], 1);
                General.sleep(1650, 2350);
            } else {
                WebWalking.walkTo(Positions.getMineArea().getRandomTile());
            }
        }
    }
}
