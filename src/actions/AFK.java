/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.actions;

import org.tribot.api.General;


public class AFK {
    public static final boolean shouldAFK() {

        // 1/4 chance user should not afk
        int rand = General.random(0,3);
        if (rand == 0) {
            return false;
        }

        return true;
    }

    public static final void sleep() {
        General.sleep(6500, 9800);
    }
}
