package ru.ndra.engine.touch;

import android.graphics.PointF;
import android.view.MotionEvent;

import ru.ndra.engine.event.Event;

public class TouchEvent extends Event {

    public int action;
    public MotionEvent originalEvent;
    public PointF pan = new PointF();
    public Pointer[] pointers;

    TouchEvent(MotionEvent event) {
        super("touch");
        this.action = event.getAction() & MotionEvent.ACTION_MASK;
        this.originalEvent = event;

        this.pointers = new Pointer[this.originalEvent.getPointerCount()];
        for (int i = 0; i < this.originalEvent.getPointerCount(); i++) {
            this.pointers[i] = new Pointer(
                    this.originalEvent.getX(i),
                    this.originalEvent.getY(i)
            );
        }

    }

    public static class Pointer {
        public final float x;
        public final float y;
        public Pointer(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }


}
