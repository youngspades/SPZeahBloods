package scripts.SPZeahBloods.src.tasks;

/**
 * Created by Adar on 6/11/17.
 */

import org.tribot.api2007.*;
import org.tribot.api2007.types.RSTile;
import org.tribot.api2007.types.RSItem;
import scripts.SPZeahBloods.src.constants.ObjectNames;
import scripts.SPZeahBloods.src.constants.ItemIds;
import scripts.SPZeahBloods.src.util.Task;

public class CraftEssence implements Task {

    @Override
    public int priority() {
        return 0;
    }

    @Override
    public boolean validate() {
        //RSTile playerPos = Player.getPosition();
        return (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) > 1 && Inventory.getCount(ItemIds.CHISEL_ID) == 1);
    }

    @Override
    public void execute() {
        RSItem[] essence = Inventory.find(ItemIds.DARK_ESSENCE_BLOCK_ID);
        RSItem chisel = Inventory.find(ItemIds.CHISEL_ID)[0];

        if (chisel != null && essence != null) {
            essence[essence.length - 1].click("Use");
            if (Game.getSelectedItemName() == ObjectNames.DENSE_RUNE_STONE_NAME) {
                chisel.click();
            } else {
                chisel.click();
            }
        }
    }
}
