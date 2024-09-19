package edu.byuh.cis.cs300.slidegameinterface;

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
     */
    private Paint room;

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

        // Set the background color to white
        c.drawColor(Color.WHITE);

        // Create a paint object for the grid lines
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.BLACK);

        // Calculate the line width as a fraction of the screen size
        float lineWidth = Math.min(w, h) * 0.01f;
        gridPaint.setStrokeWidth(lineWidth);

        // Calculate the grid size and spacing
        float gridSize = Math.min(w, h) * 0.8f;
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
    }
}
