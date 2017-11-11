package ru.ndra.engine.touch;

import android.graphics.PointF;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.ListIterator;

import ru.ndra.engine.gameobject.GameObject;
import ru.ndra.engine.gameobject.World;

/**
 * Created by golikov on 24.02.2017.
 */

public class TouchListener implements View.OnTouchListener {

    private final World world;

    private SparseArray<PointF>start = new SparseArray<PointF>();

    private int dragThreshold = 20;

    private boolean drag = false;

    private boolean multitouch = false;

    private final ArrayList<TouchEvent> motionEvents = new ArrayList<>();

    private GameObject target;

    public TouchListener(World world) {
        this.world = world;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        synchronized(motionEvents) {
            motionEvents.add(new TouchEvent(event));
        }

        return true;
    }

    public void processTouchEvents() {
        synchronized(motionEvents) {
            ListIterator<TouchEvent> eitr = motionEvents.listIterator();
            while (eitr.hasNext()) {

                TouchEvent event = eitr.next();
                prepareEvent(event);

                if(target != null) {
                    target.onTouch(event);
                }

                // Основной палец вверх
                if(event.action == MotionEvent.ACTION_UP) {

                    if (target != null) {
                        target.onClick(event);
                    }
                    drag = false;
                    target = null;
                }

            }
            motionEvents.clear();
        }
    }

    private void prepareEvent(TouchEvent event) {
        switch (event.action) {

            // Нажатие первым пальцем
            case MotionEvent.ACTION_DOWN:
                resetStartPoints(event);
                drag = false;
                multitouch = false;
                target = world.hitTest(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                resetStartPoints(event);
                multitouch = true;
                break;

            case MotionEvent.ACTION_MOVE:

                // Если касение 2 и более пальцами
                if(event.getPointerCount() > 1) {
                    int id1 = event.getPointerId(0);
                    int id2 = event.getPointerId(1);
                    PointF pos1 = new PointF(start.get(id1).x, start.get(id1).y);
                    PointF pos2 = new PointF(start.get(id2).x, start.get(id2).y);
                    double dOld = new PointF(pos1.x - pos2.x, pos1.y - pos2.y).length();
                    double dNew = new PointF(event.getX(0) - event.getX(1), event.getY(0) - event.getY(1)).length();
                    double zoom = dNew / dOld;
                    //doZoom(zoom, midPoint(event));
                }

                PointF start1 = start.get(event.getPointerId(0));
                PointF d = new PointF(event.getX() - start1.x, event.getY() - start1.y);

                if(!drag) {
                    // Определяем расстояние от точки первого касания
                    if(d.length() > dragThreshold || multitouch) {
                        drag = true;
                    }
                }

                if(drag) {

                    float cx1 = 0;
                    float cy1 = 0;
                    float cx2 = 0;
                    float cy2 = 0;
                    for(int i = 0; i < event.getPointerCount(); i ++) {
                        cx1 += event.getX(i);
                        cy1 += event.getY(i);
                        PointF startPoint = start.get(event.getPointerId(i));
                        cx2 += startPoint.x;
                        cy2 += startPoint.y;
                    }

                    event.doPan((cx1 - cx2) / event.getPointerCount(), (cy1 - cy2) / event.getPointerCount());
                    resetStartPoints(event);
                }

                break;

            // Основной палец вверх
            case MotionEvent.ACTION_UP:
                resetStartPoints(event);
                break;

            // Неосновной палце вверх
            case MotionEvent.ACTION_POINTER_UP:
                resetStartPoints(event);
                drag = false;
                break;
        }

    }

    private void resetStartPoints(TouchEvent event) {
        for(int i = 0; i < event.getPointerCount(); i ++) {
            start.put(event.getPointerId(i), new PointF(event.getX(i), event.getY(i)));
        }
    }

    /**
     * Возвращает точку поседение между двумя пальцами
     */
    private PointF midPoint(TouchEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }

}
