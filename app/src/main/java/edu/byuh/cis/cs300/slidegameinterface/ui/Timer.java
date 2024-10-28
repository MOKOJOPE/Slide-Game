package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;
/**
 * Timer class to manage the timing of token movements in the sliding game interface.
 */
public class Timer extends Handler implements TickListener{

    private static final int MESSAGE_WHAT = 1000;

    private List<GuiToken> tokens;

    private List<TickListener> ticks;


    /**
     * Constructor that initializes the Timer with a list of GuiTokens.
     *
     */
    // Constructor that takes the list of tokens
    public Timer() {
//        this.tokens = tokens;
        ticks = new ArrayList<>();

        sendMessageDelayed(obtainMessage(), 1000); // Start the timer
    }
    /**
     * Handles the message to move tokens.
     *
     * This method is called whenever a message is received. It moves each token
     * in the list and reschedules the next update.
     *
     * @param msg the Message received, which contains information for processing.
     */
    @Override
    public void handleMessage(Message msg) {
        //notifyObservers();
        // Call move on each token
//        for (GuiToken token : tokens) {
            for (TickListener subscriber: ticks){
                //token.move();
                subscriber.onTick();
//                token.stop();
            }

        //}
        sendMessageDelayed(obtainMessage(), 1000); // Reschedule for the next update
    }

    /**
     * Stops the timer by removing all pending messages.
     * This method should be called when the timer is no longer needed
     * to prevent memory leaks and unnecessary updates.
     */
    // Method to stop the timer
    public void stop() {
        removeMessages(MESSAGE_WHAT); // Stop the timer by removing all pending messages
    }

    /**
     * Subscribes a TickListener to the timer, enabling it to receive tick updates.
     *
     * @param o the TickListener to be subscribed
     */
    public void subscribe(TickListener o) {
        ticks.add(o);
    }

    /**
     * Unsubscribes a TickListener from the timer, stopping its tick updates.
     *
     * @param o the TickListener to be unsubscribed
     */
    public void unsubscribe(TickListener o) {
        ticks.remove(o);
    }

    /**
     * Notifies all subscribed TickListeners by invoking their onTick method.
     * This method is typically called within the handleMessage method to update all
     * listeners in sync with the timer's intervals.
     */
    public void notifyObservers() {
        for (TickListener listener :ticks){
            listener.onTick();
        }
    }
    /**
     * Placeholder for the onTick method from TickListener interface.
     */
    @Override
    public void onTick() {

    }
}
