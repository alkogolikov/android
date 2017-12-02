package ru.ndra.deadfall.map;

import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by golikov on 24.02.2017.
 */

public class MapCell {

    public final Pair<Integer, Integer> coords;

    public MapCell(JSONObject data) throws JSONException {
        this.coords = new Pair<>(data.getInt("x"), data.getInt("y"));
    }

}
