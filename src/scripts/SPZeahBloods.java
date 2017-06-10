/**
 * Created by Adar on 6/9/17.
 */

package scripts;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.*;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;

@ScriptManifest(authors={"Spades"}, category="Runecrafting", name="SPZeahBloods", description="Start at runestone location.")
public class SPZeahBloods extends Script {

    private State state;
    private boolean shouldCraftFull = true;

    private final String DENSE_RUNE_STONE_NAME = "Dense runestone";
    private final String BLOOD_ALTAR_NAME = "Blood Altar";

    private final int DENSE_ESSENCE_BLOCK_ID = 13445;
    private final int DARK_ESSENCE_BLOCK_ID = 13446;
    private final int DARK_ESSENCE_FRAGMENTS_ID = 7938;
    private final int CHISEL_ID = 1755;

    private final int MINE_STANDING_ANIM = 624;
    private final int MINE_CROUCHED_ANIM = 7201;

    private final RSTile MINE_TO_DARK_ALTAR_SHORTCUT_TILE = new RSTile(1761, 3872, 0);
    private final RSTile DARK_ALTAR_TO_MINE_SHORTCUT_TILE = new RSTile(1761, 3873, 0);
    private final RSTile BLOOD_ALTAR_TO_MINE_SHORTCUT_TILE = new RSTile(1743, 3854, 0);

    private final RSArea MINE_AREA = new RSArea(new RSTile[] {
            new RSTile(1769, 3871, 0),
            new RSTile(1768, 3847, 0),
            new RSTile(1757, 3847, 0),
            new RSTile(1751, 3854, 0),
    });
    private final RSArea DARK_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1723, 3878, 0),
            new RSTile(1723, 3890, 0),
            new RSTile(1709, 3887, 0),
            new RSTile(1705, 3880, 0),
    });
    private final RSArea BLOOD_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1733, 3835, 0),
            new RSTile(1736, 3826, 0),
            new RSTile(1729, 3824, 0),
            new RSTile(1714, 3825, 0),
            new RSTile(1713, 3834, 0),
            new RSTile(1721, 3832, 0),
    });

    @Override
    public void run() {
        General.useAntiBanCompliance(true);
        loop(100, 250);
    }

    private void loop(int min, int max) {
        while (true) {
            state = getState();
            switch (state) {
                case MINING:
                    handleMining();
                    break;
                case TRAVELING_TO_DARK_ALTAR:
                    break;
                case VENERATING_ESSENCE:
                    break;
                case CRAFTING_ESSENCE:
                    break;
                case RETURNING_TO_MINE:
                    break;
                case TRAVELING_TO_BLOOD_ALTAR:
                    break;
                case CREATING_BLOOD_RUNES:
                    break;
            }
            sleep(min, max);
        }
    }

    private enum State {
        MINING, TRAVELING_TO_DARK_ALTAR, VENERATING_ESSENCE, CRAFTING_ESSENCE, RETURNING_TO_MINE, TRAVELING_TO_BLOOD_ALTAR,
        CREATING_BLOOD_RUNES
    }

    private State getState() {
        if (atMine()) {
            if (Inventory.isFull()) {
                if (Inventory.getCount(DENSE_ESSENCE_BLOCK_ID) > 10) {
                    println("travel to dark altar");
                    return State.TRAVELING_TO_DARK_ALTAR;
                }
            } else {
                return State.MINING;
            }
        } else if (atDarkAltar()) {
            println("at dark altar");
            if (Inventory.isFull()) {
                if (Inventory.getCount(DENSE_ESSENCE_BLOCK_ID) > 10) {
                    println("venerate essence");
                    return State.VENERATING_ESSENCE;
                } else if (Inventory.getCount(DARK_ESSENCE_BLOCK_ID) > 0) {
                    if (Inventory.getCount(DARK_ESSENCE_FRAGMENTS_ID) > 0 && !shouldCraftFull) {
                        println("go to blood altar");
                        return State.TRAVELING_TO_BLOOD_ALTAR;
                    } else {
                        println("craft essence");
                        return State.CRAFTING_ESSENCE;
                    }
                } else {
                    println("no dark essence");
                }
            } else {
                if (Inventory.getAll().length == 3) {
                    println("Return to mine");
                    shouldCraftFull = false;
                    return State.RETURNING_TO_MINE;
                } else {
                    println("craft essence");
                    shouldCraftFull = true;
                    return State.CRAFTING_ESSENCE;
                }
            }
        } else if (atBloodAltar()) {
            println("at blood altar");
            if (Inventory.isFull()) {
                println("create blood runes");
                return State.CREATING_BLOOD_RUNES;
            } else {
                if (Inventory.getCount(DARK_ESSENCE_BLOCK_ID) > 0) {
                    println("craft essence");
                    return State.CRAFTING_ESSENCE;
                    // no blocks but yes shards
                } else if (Inventory.getCount(DARK_ESSENCE_BLOCK_ID) == 0 && Inventory.getCount(DARK_ESSENCE_FRAGMENTS_ID) > 0) {
                    println("create blood runes");
                    return State.CREATING_BLOOD_RUNES;
                    // no blocks and no shards
                } else if (Inventory.getCount(DARK_ESSENCE_BLOCK_ID) == 0 && Inventory.getCount(DARK_ESSENCE_FRAGMENTS_ID) == 0) {
                    println("go mine");
                    return State.RETURNING_TO_MINE;
                }
            }
        }

        return State.RETURNING_TO_MINE;
    }

    private boolean atMine() {
        return MINE_AREA.contains(Player.getPosition());
    }

    private boolean atDarkAltar() {
        return DARK_ALTAR_AREA.contains(Player.getPosition());
    }

    private boolean atBloodAltar() {
        return BLOOD_ALTAR_AREA.contains(Player.getPosition());
    }

    private boolean isMining() {
        int anim = Player.getAnimation();
        return (anim == MINE_STANDING_ANIM || anim == MINE_CROUCHED_ANIM);
    }

    private void handleMining() {
        RSObject[] objects = Objects.findNearest(15, DENSE_RUNE_STONE_NAME);

        if (isMining()|| Player.isMoving()) {

            if (objects.length == 2) {
                RSTile tile = objects[1].getPosition();
                Camera.turnToTile(tile);
            }
        } else {
            if (objects.length > 0) {
                println("clicking Object");
                RSTile tile = objects[0].getPosition();
                Camera.turnToTile(tile);
                DynamicClicking.clickRSObject(objects[0], 1);
            } else {
                WebWalking.walkTo(MINE_AREA.getRandomTile());
            }
        }

        sleep(475, 900);
    }
}
