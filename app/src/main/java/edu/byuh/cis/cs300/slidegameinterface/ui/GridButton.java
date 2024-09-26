package edu.byuh.cis.cs300.slidegameinterface.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;

import edu.byuh.cis.cs300.slidegameinterface.R;

public class GridButton {
    private Bitmap img;
    private RectF bounds;
    private char[] label;

    public GridButton(Resources res, float gridSize, char [] label){
        this.label = label;
        img = BitmapFactory.decodeResource(res, R.drawable.button);
        int buttonSize = (int)(gridSize / 5);
        img = Bitmap.createScaledBitmap(img, buttonSize, buttonSize, true);
        bounds = new RectF(0,0,buttonSize, buttonSize);
    }
    public void setLocation(float x, float y){
        bounds.offsetTo(x,y);
    }

    public void draw(Canvas c){
        c.drawBitmap(img, bounds.left, bounds.top, null);
    }
}
