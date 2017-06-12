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
import scripts.SPZeahBloods.SPZeahBloods;
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.constants.Positions;
import scripts.SPZeahBloods.util.Task;

public class GoToExitShortcut implements Task {

    private RSTile tile = Positions.getExitMineAgilityArea().getRandomTile();

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean validate() {
        RSTile playerPos = Player.getPosition();
        return (Inventory.isFull() && !Positions.atDarkAltar() && tile.distanceTo(playerPos) > 8 &&
                Positions.DARK_ALTAR_TO_MINE_SHORTCUT_TILE.distanceTo(playerPos) > Positions.MINE_TO_DARK_ALTAR_SHORTCUT_TILE.distanceTo(playerPos));
    }

    @Override
    public void execute() {
        if (!Walking.clickTileMM(tile, 1))
            WebWalking.walkTo(Positions.getExitMineAgilityArea().getRandomTile());

        SPZeahBloods.aCamera.turnToTile(tile);
        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100); // Add this in to reduce CPU usage
                return (!Player.isMoving());
            }
        }, General.random(1350, 2350));
    }
}
