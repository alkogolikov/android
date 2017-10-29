package ru.ndra.engine;

import android.graphics.PointF;
import android.view.MotionEvent;

public class TouchEvent {

    public int action;
    private float x[];
    private float y[];
    private int[] ids;
    private final MotionEvent event;
    private final TouchListener listener;

    public PointF pan = new PointF();

    public TouchEvent(MotionEvent event, TouchListener listener) {
        this.action = event.getAction() & MotionEvent.ACTION_MASK;
        this.x = new float[event.getPointerCount()];
        this.y = new float[event.getPointerCount()];
        this.ids = new int[event.getPointerCount()];
        this.event = event;
        this.listener = listener;
        for (int i = 0; i < event.getPointerCount(); i++) {
            this.x[i] = event.getX(i);
            this.y[i] = event.getY(i);
            this.ids[i] = event.getPointerId(i);
        }
    }

    public float getX() {
        return x[0];
    }

    public float getY() {
        return y[0];
    }

    public int getPointerId(int i) {
        return ids[i];
    }

    public float getPointerCount() {
        return this.x.length;
    }

    public float getX(int i) {
        return this.x[i];
    }

    public float getY(int i) {
        return this.y[i];
    }

    public void doPan(float x, float y) {
        pan.x = x;
        pan.y = y;
    }

}
