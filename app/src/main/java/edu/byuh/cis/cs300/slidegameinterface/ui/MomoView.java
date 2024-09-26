package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * A custom View class for drawing a grid on the screen.
 */

public class MomoView extends View{

    /**
     * The paint object for the room.
     * The boolean object for checking whether run or not of the if statement.
     */
    private Paint room;
    private boolean initialized;

    //Call to draw
    /**
     * Constructor for the MomoView class.
     *
     * @param c the Context object
     */
    public MomoView(Context c){
        super(c);
        room = new Paint();
        room.setColor(Color.BLACK);
    }
//Draw

    /**
     * Draws the grid on the screen.
     *
     * @param c the Canvas object
     */
    @Override
    public void onDraw(Canvas c){
        super.onDraw(c);
        float w = getWidth();
        float h = getHeight();
        float gridSize = Math.min(w, h) * 0.6f;
        float a = w*0.2f;
        float b = h*0.309f;
        char[] num = {'1', '2', '3', '4', '5'};
        char[] letter = {'A', 'B', 'C', 'D', 'E'};

        //Create an array of GridButton objects so that I can draw multiple button
        GridButton[] xbuttons = new GridButton[5];
        GridButton[] ybuttons = new GridButton[5];

        //Setting the location to draw the button
        if (!initialized) {
            for (int t = 0; t < 5; t++) {
                xbuttons[t] = new GridButton(getResources(), gridSize, num);
                xbuttons[t].setLocation(a, b);
                a += gridSize/5;
            }
            a = w*0.1f;
            for (int t = 0; t < 5; t++) {
                ybuttons[t] = new GridButton(getResources(), gridSize, letter);
                ybuttons[t].setLocation(a - 28f, b + gridSize/5);
                b += gridSize/5;
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
        for(GridButton button : xbuttons){
            button.draw(c);
        }

        for(GridButton button : ybuttons){
            button.draw(c);
        }

    }
}
