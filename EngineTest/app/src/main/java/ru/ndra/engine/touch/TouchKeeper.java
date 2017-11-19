package ru.ndra.engine.touch;

import android.view.MotionEvent;

import java.util.ArrayList;

import ru.ndra.engine.event.Event;
import ru.ndra.engine.event.EventManager;

public class TouchKeeper {

    public ArrayList<TouchEvent.Pointer> pointers = new ArrayList<>();

    public TouchKeeper(EventManager eventManager) {
        eventManager.on("touch", (Event event) -> {
            this.handleTouchEvent((TouchEvent) event);
        });
    }

    private void handleTouchEvent(TouchEvent event) {

        this.pointers = new ArrayList<>();
        for (int i = 0; i < event.originalEvent.getPointerCount(); i++) {

            // Поднятые пальцы исключаем
            if (event.action == MotionEvent.ACTION_POINTER_UP || event.action == MotionEvent.ACTION_UP) {
                if (i == event.originalEvent.getActionIndex()) {
                    continue;
                }
            }

            TouchEvent.Pointer pointer = new TouchEvent.Pointer(
                    event.originalEvent.getX(i),
                    event.originalEvent.getY(i)
            );
            this.pointers.add(pointer);
        }


    }

}
