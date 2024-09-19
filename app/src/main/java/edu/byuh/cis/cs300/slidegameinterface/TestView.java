package edu.byuh.cis.cs300.slidegameinterface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class TestView extends View{

    private Paint room;

    public TestView(Context c){
        super(c);
        room = new Paint();
        room.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas c){
        super.onDraw(c);
        float w = getWidth();
        float h = getHeight();
//      For row
        float linex1 = (float)(w * 0.2);
        float liney = (float) (h * 0.3);
        float linex2 = (float)(w * 0.7f);

//       For Column
        float lineyy = (float) (h * 0.54f);

        room.setStrokeWidth(w * 0.01f);
        c.drawColor(Color.WHITE);
        //Draw 6 lines for row
        c.drawLine(linex1, liney, linex2, liney, room);
        c.drawLine(linex1, liney + (h * 0.048f), linex2, liney + (h * 0.048f), room);
        c.drawLine(linex1, liney + (h * 0.096f), linex2, liney + (h * 0.096f), room);
        c.drawLine(linex1, liney + (h * 0.144f), linex2, liney + (h * 0.144f), room);
        c.drawLine(linex1, liney + (h * 0.192f), linex2, liney + (h * 0.192f), room);
        c.drawLine(linex1, liney + (h * 0.240f), linex2, liney + (h * 0.240f), room);

        //Draw 6 lines for column
        c.drawLine(linex1 , liney, linex1, lineyy, room);
        c.drawLine(linex1 + (w * 0.1f) , liney, linex1 + (w * 0.1f), lineyy, room);
        c.drawLine(linex1 + (w * 0.2f) , liney, linex1 + (w * 0.2f), lineyy, room);
        c.drawLine(linex1 + (w * 0.3f) , liney, linex1 + (w * 0.3f), lineyy, room);
        c.drawLine(linex1 + (w * 0.4f) , liney, linex1 + (w * 0.4f), lineyy, room);
        c.drawLine(linex1 + (w * 0.5f) , liney, linex1 + (w * 0.5f), lineyy, room);



    }
}
