package scripts.SPZeahBloods.tasks;

/**
 * Created by Adar on 6/11/17.
 */

import javafx.geometry.Pos;
import obf.PO;
import org.tribot.api.General;
import org.tribot.api.DynamicClicking;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.types.RSItem;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.constants.Animations;
import scripts.SPZeahBloods.constants.ObjectNames;
import scripts.SPZeahBloods.constants.ItemIds;
import scripts.SPZeahBloods.constants.Positions;
import scripts.SPZeahBloods.actions.AFK;
import scripts.SPZeahBloods.util.Task;

public class CraftEssence implements Task {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean validate() {
        RSTile playerPos = Player.getPosition();
        return (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) > 1 && Inventory.getCount(ItemIds.CHISEL_ID) == 1) &&
                Positions.atDarkAltar();
    }

    @Override
    public void execute() {
        RSItem[] essence = Inventory.find(ItemIds.DARK_ESSENCE_BLOCK_ID);
        RSItem chisel = Inventory.find(ItemIds.CHISEL_ID)[0];

        if (chisel != null && essence != null) {
            essence[essence.length - 1].click("Use");
            if (Game.getSelectedItemName() == ObjectNames.DENSE_RUNE_STONE_NAME) {
                chisel.click("Use");
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100); // Add this in to reduce CPU usage
                        return (Player.getAnimation() != Animations.CRAFT_ANIM);
                    }
                }, General.random(1350, 2350));
            }
        }
    }
}
