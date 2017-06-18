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

public class GoToBloodAltar implements Task {

    private RSTile tile = Positions.getBloodAltarArea().getRandomTile();

    @Override
    public int priority() {
        return 2;
    }

    @Override
    public boolean validate() {
        return (Inventory.isFull() && (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) >= 1)
                && (Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) >= 1)
                && SPZeahBloods.shouldGoToBloodAltar
                && !Positions.atBloodAltar()
        );
    }

    @Override
    public void execute() {
        General.println("WALKINGGGGGG2");
        if (WebWalker.walkTo(tile)) {

        } else {
            General.println("BLOOD ALTAR BROKEN");
            General.println(tile.toString());
        }
        General.println("WALKINGGGGGG3");
        General.sleep(1000, 1500);
        Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100); // Add this in to reduce CPU usage
                return (!Player.isMoving());
            }
        }, General.random(1350, 2350));
    }
}
