package scripts.SPZeahBloods.src.tasks;

/**
 * Created by Adar on 6/11/17.
 */

import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSTile;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.src.SPZeahBloods;
import scripts.SPZeahBloods.src.constants.Animations;
import scripts.SPZeahBloods.src.constants.ItemIds;
import scripts.SPZeahBloods.src.constants.Positions;
import scripts.SPZeahBloods.src.util.Task;

public class GoOverEntranceShortcut implements Task {

    private RSTile tile = Positions.DARK_ALTAR_TO_MINE_SHORTCUT_TILE;

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean validate() {
        RSTile playerPos = Player.getPosition();
        return (Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) > 0 &&  tile.distanceTo(playerPos) <= 9 &&
                Positions.MINE_TO_DARK_ALTAR_SHORTCUT_TILE.distanceTo(playerPos) > Positions.DARK_ALTAR_TO_MINE_SHORTCUT_TILE.distanceTo(playerPos));
    }

    @Override
    public void execute() {
        SPZeahBloods.aCamera.turnToTile(tile);
        Walking.clickTileMS(tile, "rocks");
        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100); // Add this in to reduce CPU usage
                return (!Player.isMoving() && Player.getAnimation() != Animations.CLIMB_ANIM);
            }
        }, General.random(1250, 2200));
    }
}
