package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import edu.byuh.cis.cs300.slidegameinterface.R;
/**
 * This class represents a button that can be drawn on a canvas.
 * It has a bitmap image and can switch between normal and pressed states.
 */
public class GridButton {
    private Bitmap img;
    private Bitmap passedImg;
    private RectF bounds;
    private char[] label;
    private boolean isPassed;
    private float x;
    private float y;


    /**
     *
     * @param res the resources to use for the button image
     * @param gridSize the size of the grid that the button belongs to
     * @param label the label to display on the button
     */
    public GridButton(Resources res, float gridSize, char [] label, float x, float y, float size){
        this.label = label;
        img = BitmapFactory.decodeResource(res, R.drawable.button);
        passedImg = BitmapFactory.decodeResource(res, R.drawable.passed);
        int buttonSize = (int)(gridSize / 5);
        img = Bitmap.createScaledBitmap(img, buttonSize, buttonSize, true);
        passedImg = Bitmap.createScaledBitmap(passedImg, buttonSize, buttonSize, true);
        bounds = new RectF(x,y, x+size, y+size);
        isPassed = false;
    }

    /**
     * Sets the location of the button on the canvas.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     */
    public void setLocation(float x, float y){
        bounds.offsetTo(x,y);
    }

    /**
     * Checks if the button contains the given coordinates.
     *
     * @param x the x-coordinate to check
     * @param y the y-coordinate to check
     * @return true if the (x, y) coordinates are within the button bounds, false otherwise
     */
    public boolean contains(float x, float y){
        return bounds.contains(x,y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public RectF getBounds() {
        return bounds;
    }

    /**
     * Marks the button as pressed by changing its state.
     */
    public void press(){
        isPassed = true;
    }

    /**
     * Marks the button as released by reverting its state to normal.
     */
    public void release(){
        isPassed = false;
    }

    /**
     * Draws the button on the provided canvas.
     * Depending on its state, it draws either the normal or the pressed image.
     *
     * @param c the canvas to draw the button on
     */
    public void draw(Canvas c){
        if(isPassed) {
            c.drawBitmap(passedImg, bounds.left, bounds.top, null);
        }else {
            c.drawBitmap(img, bounds.left, bounds.top, null);
        }

    }
}
