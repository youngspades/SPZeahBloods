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

    private static final RSArea DARK_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1723, 3878, 0),
            new RSTile(1723, 3890, 0),
            new RSTile(1709, 3887, 0),
            new RSTile(1705, 3880, 0),
    });

    public static final RSArea getDarkAltarArea() {
        return Positions.DARK_ALTAR_AREA;
    }

    private static final RSArea BLOOD_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1733, 3835, 0),
            new RSTile(1736, 3826, 0),
            new RSTile(1729, 3824, 0),
            new RSTile(1714, 3825, 0),
            new RSTile(1713, 3834, 0),
            new RSTile(1721, 3832, 0),
    });

    public static final RSArea getBloodAltarArea() {
        return Positions.BLOOD_ALTAR_AREA;
    }

    private static final RSArea EXIT_MINE_AGILITY_AREA = new RSArea(new RSTile[] {
            new RSTile(1761, 3871, 0),
            new RSTile(1766, 3869, 0),
            new RSTile(1757, 3868, 0),
    });

    public static final RSArea getExitMineAgilityArea() {
        return Positions.EXIT_MINE_AGILITY_AREA;
    }

    private static final RSArea ENTER_MINE_AGILITY_AREA = new RSArea(new RSTile[] {
            new RSTile(1757, 3879, 0),
            new RSTile(1756, 3873, 0),
            new RSTile(1761, 3871, 0),
            new RSTile(1763, 3880, 0),
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
