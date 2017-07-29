package ru.ndra.deadfall;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by golikov on 24.02.2017.
 */

public class MapCell {

    private final Map map;
    public final int x;
    public final int y;
    public final int type;

    public MapCell(Map map, JSONObject data) {
        try {
            this.map = map;
            x = data.getInt("x");
            y = data.getInt("y");
            type = data.getInt("type");
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("MapCell JSON error");
        }
    }

    /**
     * Создает пустую ячейку
     * @param map
     * @param x
     * @param y
     */
    public MapCell(Map map, int x, int y) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.type = 0;
    }

}
