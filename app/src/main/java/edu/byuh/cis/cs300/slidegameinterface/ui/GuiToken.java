package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.ArrayList;

import edu.byuh.cis.cs300.slidegameinterface.R;
import edu.byuh.cis.cs300.slidegameinterface.logic.Player;


/**
 * Represents a graphical token in the sliding game interface.
 * The token is associated with a player (X or O) and can be drawn on the canvas.
 */
public class GuiToken implements TickListener{
    private Bitmap img;// library to show the image
    private RectF bounds; //making the bounds of Rectangle with 4 values
    private Player player;
    private PointF velocity;//class to contain the float values have 2 points
    public PointF destination;
    private float tolerance;
    private GridPosition gp;
    private static int movers;


    public static class GridPosition{
        public char row;
        public char column;

    }

    /**
     * Constructor for the GuiToken class.
     * Initializes the token with an image, player type, size, and position.
     *
     * @param res the Resources object to load the image
     * @param player the player type (X or O)
     * @param x the starting X position
     * @param y the starting Y position
     * @param size the size of each grid cell
     * @param b the GridButton to determine the token's initial position
     */
    public GuiToken(Resources res, Player player, float x, float y, float size, GridButton b) {
        this.player = player;
        if (player == Player.X) {
            img = BitmapFactory.decodeResource(res, R.drawable.button_x);
        } else {
            img = BitmapFactory.decodeResource(res, R.drawable.button_o);
        }

        // Scale the image to fit the grid cell
        img = Bitmap.createScaledBitmap(img, (int) size - 25, (int) size - 25, true);

//        if (b.isTopButton()){
//            moveDown();
//        }else{
//            moveRight();
//        }

        // Set the initial position and size
        bounds = new RectF(x, y, x + size, y + size);
        // Initialize velocity (for sliding animation)
        velocity = new PointF(0, 0);

        destination = new PointF();

        tolerance = bounds.height() / 10f;

        gp = new GridPosition();
        if (b.isTopButton()) {
            gp.row = (char)('A' - 1 );
            gp.column = b.getLabel();
        } else if (b.isLeftButton()) {
            gp.row = b.getLabel();
            gp.column = (char)('1' - 1 );
        }
        movers = 0;
    }
    /**
     * Sets the location of the token to the specified coordinates.
     *
     * @param x the new X position of the token
     * @param y the new Y position of the token
     */
    public void setLocation(float x, float y){
        bounds.offsetTo(x,y);
    }

    /**
     * Moves the token based on its velocity.
     */
    public void move() {
        if (velocity.x != 0 || velocity.y != 0){
//            float dx = destination.x - bounds.left;
//            float dy = destination.y - bounds.top;
//            if (PointF.length(dx,dy) < tolerance){
                bounds.offset(velocity.x, velocity.y);
                velocity.set(0,0);
//                GuiToken.stopMoving();
//            }else{
//                bounds.offset(velocity.x, velocity.y);
//            }
        }

    }


    /**
     * Stops the movement of the token by setting its velocity to zero.
     */
    public void stop(){
        velocity.set(0,0);
        GuiToken.stopMoving();
    }
    /**
     * Draws the token on the canvas.
     *
     * @param c the Canvas to draw on
     */
    public void draw(Canvas c) {
        c.drawBitmap(img, bounds.left, bounds.top, null);
    }
    /**
     * Sets the velocity of the token.
     *
     * @param dx the change in X position
     * @param dy the change in Y position
     */
    // Getter and setter for velocity, if needed
    public void setVelocity(float dx, float dy) {
        velocity.set(dx, dy);

    }
    /**
     * Gets the bounds of the token.
     *
     * @return the RectF object representing the bounds of the token
     */
    public RectF getBounds() {
        return bounds;
    }
    /**
     * Gets the player type associated with the token.
     *
     * @return the Player object representing the token's player
     */
    public Player getPlayer() {
        return player;
    }

//    public void moveDown(){
//        setGoal(bounds.left, bounds.top+bounds.height());
//    }
//
//    public void moveRight(){
//        setGoal(bounds.left+bounds.width(), bounds.top);
//    }
//
//    private void setGoal(float x, float y){
//        destination.set(x,y);
//        float dx = destination.x - bounds.left;
//        float dy = destination.y - bounds.top;
//        velocity.x = dx/11f;
//        velocity.y = dy/11f;
//    }
    /**
     * Gets the grid position of the token.
     *
     * @return the GridPosition object representing the token's position on the grid
     */
    public GridPosition getGridPosition(){
        return gp;
    }
    /**
     * Starts the movement counter for tokens.
     */
    public static void startMoving(){
        movers++;
    }
    /**
     * Stops the movement counter for tokens.
     */
    public static void stopMoving(){
        movers--;
    }
    /**
     * Checks if any token is currently moving.
     *
     * @return true if any token is moving, false otherwise
     */
    public static boolean isAnyTokenMoving(){
        return movers > 0;
    }

    /**
     * Called on each tick to update the token's position.
     * Moves the token based on its current velocity.
     */
    @Override
    public void onTick() {
        move();
    }
}