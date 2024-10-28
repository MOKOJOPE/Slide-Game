package edu.byuh.cis.cs300.slidegameinterface.ui;
/**
 * Interface for objects that require periodic updates, such as in a timer-driven context.
 * Classes implementing this interface should define behavior for the onTick method,
 * which will be called on each timer tick.
 */
public interface TickListener {
    void onTick();
}

