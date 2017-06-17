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
import scripts.SPZeahBloods.src.constants.ItemIds;
import scripts.SPZeahBloods.src.constants.Positions;
import scripts.SPZeahBloods.src.util.Task;
import scripts.webwalker_logic.*;

public class GoToDarkAltarShortcut implements Task {

    private RSTile tile = Positions.getEnterMineAgilityArea().getRandomTile();

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean validate() {
        RSTile playerPos = Player.getPosition();
        return (!Inventory.isFull() && Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) > 0
                && (Positions.getDarkAltarArea().getRandomTile().distanceTo(playerPos)
                    < Positions.getMineArea().getRandomTile().distanceTo(playerPos))
                && (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) <= 0));
    }

    @Override
    public void execute() {
        SPZeahBloods.shouldGoToBloodAltar = true;
        WebWalker.walkTo(tile);

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

