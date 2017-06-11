package scripts.SPZeahBloods.util;

/**
 * @author Encoded
 */

public interface Task {

    int priority();

    boolean validate();

    void execute();

}