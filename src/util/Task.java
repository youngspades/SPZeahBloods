package scripts.SPZeahBloods.src.util;

/**
 * @author Encoded
 */

public interface Task {

    int priority();

    boolean validate();

    void execute();

}