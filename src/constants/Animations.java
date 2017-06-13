/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.src.constants;

import org.tribot.api2007.Player;

public class Animations {
    public static final int MINE_STANDING_ANIM = 624;
    public static final int MINE_CROUCHED_ANIM = 7201;
    public static final boolean isMining() {
        int anim = Player.getAnimation();
        return (anim == Animations.MINE_STANDING_ANIM || anim == Animations.MINE_CROUCHED_ANIM);
    }

    public static final int CRAFT_ANIM = 7202;
    public static final int CLIMB_ANIM = 839;
    public static final int VENERATE_ANIM = 645;
}
