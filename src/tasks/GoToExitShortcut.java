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
import scripts.SPZeahBloods.src.constants.Positions;
import scripts.SPZeahBloods.src.util.Task;
import scripts.webwalker_logic.*;

public class GoToExitShortcut implements Task {

    private RSTile tile = Positions.getExitMineAgilityArea().getRandomTile();

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean validate() {
        RSTile playerPos = Player.getPosition();
        return (Inventory.isFull() && !Positions.atDarkAltar() &&
                Positions.DARK_ALTAR_TO_MINE_SHORTCUT_TILE.distanceTo(playerPos) > Positions.MINE_TO_DARK_ALTAR_SHORTCUT_TILE.distanceTo(playerPos));
    }

    @Override
    public void execute() {
        if (WebWalker.walkTo(tile)) {
            General.println("WALKINGGGGGG");
        } else {
            General.println("WALKINGGGGGG1");
            General.println("FAULTY TILE");
            General.println(tile.toString());
        }
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
