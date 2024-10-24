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

    private List<TickListener> subscribers = new ArrayList<>();


    /**
     * Constructor that initializes the Timer with a list of GuiTokens.
     *
     * @param tokens a List of GuiToken objects that will be moved by the timer.
     */
    // Constructor that takes the list of tokens
    public Timer(List<GuiToken> tokens) {
        this.tokens = tokens;
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
        // Call move on each token
        for (GuiToken token : tokens) {
            for (TickListener subscriber: subscribers){
                token.move();
                subscriber.onTick();
//              token.stop();
                break;
            }

        }
        sendMessageDelayed(obtainMessage(), 1000); // Reschedule for the next update
    }

    /**
     * Stops the timer by removing all pending messages.
     *
     * This method should be called when the timer is no longer needed
     * to prevent memory leaks and unnecessary updates.
     */
    // Method to stop the timer
    public void stop() {
        removeMessages(MESSAGE_WHAT); // Stop the timer by removing all pending messages
    }


    public void subscribe(TickListener o) {
        subscribers.add(o);
    }


    public void unsubscribe(TickListener o) {
        subscribers.remove(o);
    }


    public void notifyObservers() {
        for (TickListener o :subscribers){
            o.onTick();
        }
    }

    @Override
    public void onTick() {

    }
}
