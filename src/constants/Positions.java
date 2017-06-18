/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.src.constants;

import javafx.geometry.Pos;
import obf.PO;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Positions {
    public static final RSTile MINE_TO_DARK_ALTAR_SHORTCUT_TILE = new RSTile(1761, 3872, 0);
    public static final RSTile DARK_ALTAR_TO_MINE_SHORTCUT_TILE = new RSTile(1761, 3873, 0);
    public static final RSTile BLOOD_ALTAR_TO_MINE_SHORTCUT_TILE = new RSTile(1743, 3854, 0);

    private static final RSArea SEE_MINE_AREA = new RSArea(new RSTile[] {
            new RSTile(1759, 3849, 0),
            new RSTile(1759, 3857, 0),
            new RSTile(1768, 3851, 0)
    });

    public static final RSArea getSeeMineArea() {
        return Positions.SEE_MINE_AREA;
    }

    private static final RSArea MINE_AREA = new RSArea(new RSTile[] {
            new RSTile(1769, 3871, 0),
            new RSTile(1768, 3847, 0),
            new RSTile(1757, 3847, 0),
            new RSTile(1748, 3854, 0),
    });

    public static final RSArea getMineArea() {
        return Positions.MINE_AREA;
    }

    private static final RSArea GO_TO_DARK_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1715, 3878, 0),
            new RSTile(1721, 3878, 0),
            new RSTile(1721, 3889, 0),
            new RSTile(1715, 3889, 0),
            new RSTile(1715, 3885, 0),
            new RSTile(1715, 3885, 0),
            new RSTile(1718, 3881, 0),
            new RSTile(1715, 3881, 0),
    });

    public static final RSArea getGoToDarkAltarArea() {
        return Positions.GO_TO_DARK_ALTAR_AREA;
    }

    private static final RSArea DARK_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1715, 3878, 0),
            new RSTile(1721, 3878, 0),
            new RSTile(1721, 3889, 0),
            new RSTile(1715, 3889, 0),
    });

    public static final RSArea getDarkAltarArea() {
        return Positions.DARK_ALTAR_AREA;
    }

    private static final RSArea BLOOD_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1719, 3826, 0),
            new RSTile(1722, 3826, 0),
            new RSTile(1722, 3830, 0),
            new RSTile(1719, 3831, 0),
    });

    public static final RSArea getBloodAltarArea() {
        return Positions.BLOOD_ALTAR_AREA;
    }

    private static final RSArea EXIT_MINE_AGILITY_AREA = new RSArea(new RSTile[] {
            new RSTile(1756, 3866, 0),
            new RSTile(1756, 3868, 0),
            new RSTile(1760, 3869, 0),
    });

    public static final RSArea getExitMineAgilityArea() {
        return Positions.EXIT_MINE_AGILITY_AREA;
    }

    private static final RSArea BLOOD_ALTAR_SHORTCUT_AREA = new RSArea(new RSTile[] {
            new RSTile(1742, 3855, 0),
            new RSTile(1737, 3855, 0),
            new RSTile(1737, 3851, 0),
            new RSTile(1742, 3851, 0),
    });

    public static final RSArea getBloodAltarShortcutArea() {
        return Positions.BLOOD_ALTAR_SHORTCUT_AREA;
    }

    private static final RSArea ENTER_MINE_AGILITY_AREA = new RSArea(new RSTile[] {
            new RSTile(1758, 3876, 0),
            new RSTile(1761, 3876, 0),
            new RSTile(1761, 3878, 0),
            new RSTile(1757, 3878, 0),
    });

    public static final RSArea getEnterMineAgilityArea() {
        return Positions.ENTER_MINE_AGILITY_AREA;
    }

    public static boolean atMine() {
        return Positions.MINE_AREA.contains(Player.getPosition());
    }

    public static boolean atDarkAltar() {
        return Positions.DARK_ALTAR_AREA.contains(Player.getPosition());
    }

    public static  boolean atBloodAltar() {
        return Positions.BLOOD_ALTAR_AREA.contains(Player.getPosition());
    }
}
