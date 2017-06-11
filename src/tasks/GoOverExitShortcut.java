package scripts.SPZeahBloods.tasks;

/**
 * Created by Adar on 6/11/17.
 */

import javafx.geometry.Pos;
import org.tribot.api.General;
import org.tribot.api.DynamicClicking;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.constants.Positions;
import scripts.SPZeahBloods.actions.AFK;
import scripts.SPZeahBloods.util.Task;

public class GoOverExitShortcut implements Task {

    private RSTile tile = Positions.MINE_TO_DARK_ALTAR_SHORTCUT_TILE;

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean validate() {
        return (Inventory.isFull() && !Positions.atDarkAltar() && tile.distanceTo(Player.getPosition()) <= 8);
    }

    @Override
    public void execute() {
        Walking.clickTileMS(tile, "rocks");
        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100); // Add this in to reduce CPU usage
                return (!Player.isMoving() || Player.getAnimation() != Animations.CLIMB_ANIM);
            }
        }, General.random(1350, 2350));
    }
}
