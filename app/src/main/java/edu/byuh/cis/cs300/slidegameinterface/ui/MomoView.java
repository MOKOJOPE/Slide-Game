package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import android.os.Handler;

import edu.byuh.cis.cs300.slidegameinterface.logic.Player;

/**
 * A custom View class that draws a grid on the screen with buttons along the X and Y axes.
 * The buttons respond to touch events and toggle between normal and pressed states.
 */

public class MomoView extends View implements TickListener{

    private Paint room;
    private boolean initialized;
    private float lastX;
    private float lastY;
    //Create an array of GridButton objects so that I can draw multiple button
    private GridButton[] xbuttons = new GridButton[5];
    private GridButton[] ybuttons = new GridButton[5];
    private ArrayList<GuiToken> tokens;
    private boolean isPlayerXTurn = true;
    private char tokenList;
    private Timer tokenTimer;
    char[] num = {'1', '2', '3', '4', '5'};
    char[] letter = {'A', 'B', 'C', 'D', 'E'};

    //Call to draw

    /**
     * Constructor for the MomoView class.
     * Initializes the paint object for drawing and sets the color.
     *
     * @param c the Context object, typically the activity in which the view is created
     */
    public MomoView(Context c) {
        super(c);
        room = new Paint();
        room.setColor(Color.BLACK);
        tokens = new ArrayList<>();
        tokenTimer = new Timer(tokens);

    }
//Draw

    /**
     * Draws the grid and buttons on the screen.
     * The grid lines are centered on the screen, and buttons are placed along the X and Y axes.
     *
     * @param c the Canvas object where the grid and buttons are drawn
     */
    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        float w = getWidth();
        float h = getHeight();
        float gridSize = Math.min(w, h) * 0.6f;
        float a = w * 0.2f;
        float b = h * 0.309f;



        //Setting the location to draw the button
        if (!initialized) {
            for (int t = 0; t < 5; t++) {
                xbuttons[t] = new GridButton(getResources(), gridSize, num, a, b, gridSize/5);
                a += gridSize/5;
            }
            a = w * 0.1f;
            for (int t = 0; t < 5; t++) {
                ybuttons[t] = new GridButton(getResources(), gridSize, letter, a-28f ,b + gridSize/5, gridSize/5);
//                ybuttons[t].setLocation(a - 28f, b + gridSize / 5);
                b += gridSize / 5;
            }
            initialized = true;
        }

        // Set the background color to white
        c.drawColor(Color.WHITE);

        // Create a paint object for the grid lines
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.BLACK);

        // Calculate the line width as a fraction of the screen size
        float lineWidth = Math.min(w, h) * 0.01f;
        gridPaint.setStrokeWidth(lineWidth);

        // Calculate the grid size and spacing
//        float gridSize = Math.min(w, h) * 0.8f;
        float gridSizeHalf = gridSize / 2;
        float gridSpacing = gridSize / 5;

        // Calculate the center of the screen
        float centerX = w / 2;
        float centerY = h / 2;

        // Draw the grid lines
        for (int i = 0; i < 6; i++) {
            float x = centerX - gridSizeHalf + i * gridSpacing;
            c.drawLine(x, centerY - gridSizeHalf, x, centerY + gridSizeHalf, gridPaint);
            c.drawLine(centerX - gridSizeHalf, centerY - gridSizeHalf + i * gridSpacing, centerX + gridSizeHalf, centerY - gridSizeHalf + i * gridSpacing, gridPaint);
        }

        //Draw x asix button
        for (GridButton button : xbuttons) {
            button.draw(c);
        }

        //Draw y asix button
        for (GridButton button : ybuttons) {
            button.draw(c);
        }

        for (GuiToken token : tokens) {
            token.draw(c);
        }

    }

    /**
     * Handles touch events to determine if a button was pressed.
     * If a button is pressed, it changes its state and adds a token.
     * If no button is pressed, a toast message is shown.
     *
     * @param m the MotionEvent object containing details about the touch event
     * @return true if the touch event is handled; otherwise, false
     */
    @Override
    public boolean onTouchEvent(MotionEvent m) {
        float w = getWidth();
        float h = getHeight();
        lastX = m.getX();
        lastY = m.getY();
        boolean buttonPressed = false;
        float gridSize = Math.min(w, h) * 0.6f;
        float tokenSize = gridSize / 5; // Use the same size as the buttons
//        // Arrays to track how many tokens are dropped per column or row
//        int[] xTokenCount = new int[xbuttons.length]; // Track number of tokens in each X column
//        int[] yTokenCount = new int[ybuttons.length]; // Track number of tokens in each Y row


        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            for (int i = 0; i < xbuttons.length; i++) {
                GridButton button = xbuttons[i];
                if (button.contains(lastX, lastY) && !buttonPressed) {
                    button.press();
                    buttonPressed = true;
                    Log.d("CS300", "You passed the button!");

                    // Get the position of the button
                    RectF bound = button.getBounds();

                    if (isPlayerXTurn) { // Check if it's Player X's turn
                        tokenList = 'X'; // Assign 'X' if true
                        GuiToken token = new GuiToken(getResources(), Player.X, bound.left, bound.top, tokenSize);
                        token.setLocation(bound.left+15, bound.top + tokenSize+20); // Set the position where the button was pressed
                        token.setVelocity(0, tokenSize);
                        token.stop();
                        tokens.add(token); // Add the token to the list
                        // Switch the turn
                        isPlayerXTurn = false;

                    } else { // It's Player O's turn
                        tokenList = 'O'; // Assign 'O' if false
                        GuiToken token = new GuiToken(getResources(), Player.O, bound.left, bound.top, tokenSize);
                        token.setLocation(bound.left+15, bound.top +tokenSize+20); // Set the position where the button was pressed
                        token.setVelocity(0, tokenSize);
                        token.stop();
                        tokens.add(token); // Add the token to the list
                        // Switch the turn
                        isPlayerXTurn = true;
                    }
                }
            }

            for (GridButton button : ybuttons) {
                if (button.contains(lastX, lastY) && !buttonPressed) {
                    button.press();
                    buttonPressed = true;
                    Log.d("CS300", "You passed the button!");

                    // Get the position of the button
                    RectF bound = button.getBounds();

                    if (isPlayerXTurn) { // Check if it's Player X's turn
                        tokenList = 'X'; // Assign 'X' if true
                        GuiToken token = new GuiToken(getResources(), Player.X, bound.left, bound.top, tokenSize);
                        token.setLocation(bound.left + tokenSize+20, bound.top+20); // Set the position where the button was pressed
                        token.setVelocity(tokenSize, 0);
                        token.stop();
                        tokens.add(token); // Add the token to the list
                        // Switch the turn
                        isPlayerXTurn = false;

                    } else { // It's Player O's turn
                        tokenList = 'O'; // Assign 'O' if false
                        GuiToken token = new GuiToken(getResources(), Player.O, bound.left, bound.top, tokenSize);
                        token.setLocation(bound.left + tokenSize+20, bound.top+20); // Set the position where the button was pressed
                        token.setVelocity(tokenSize, 0);
                        token.stop();
                        tokens.add(token); // Add the token to the list
                        // Switch the turn
                        isPlayerXTurn = true;
                    }
                }
            }
            if (!buttonPressed) {
                Toast.makeText(getContext(), "Please touch one of the buttons", Toast.LENGTH_LONG).show();
            }
            invalidate();
        } else if (m.getAction() == MotionEvent.ACTION_UP) {
            for (GridButton button : xbuttons) {
                if (button.contains(lastX, lastY)) {
                    button.release();
                    Log.d("CS300", "You released the button!");
                    }
                }
            }

            for (GridButton button : ybuttons) {
                if (button.contains(lastX, lastY)) {
                    button.release();
                    Log.d("CS300", "You released the button!");
                }
            buttonPressed = false;
            }

            invalidate();
            return true;
    }

    @Override
    public void onTick() {
        invalidate();
    }
}
