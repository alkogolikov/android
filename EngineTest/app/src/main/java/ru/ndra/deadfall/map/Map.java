package ru.ndra.deadfall.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

import ru.ndra.deadfall.console.ConsoleService;
import ru.ndra.engine.ResourceLoader;

/**
 * Класс для работы с картой
 */
public class Map {

    private final Context context;
    private final ConsoleService console;
    private ResourceLoader loader;
    private HashMap<Pair<Integer, Integer>, MapCell> cells = new HashMap<>();

    public int width = 0;
    public int height = 0;

    public Map(Context context, ConsoleService console, ResourceLoader loader) {
        this.context = context;
        this.console = console;
        this.loader = loader;
        this.loadData();
    }

    // @todo сделать загрузку данных в трэде
    public void loadData() {
        try {

            // Загружаем карту из JSON
            InputStream is = this.context.getAssets().open("data/map.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject obj = new JSONObject(json);

            // Создаем ячейки карты
            JSONArray mapData = obj.getJSONArray("cells");
            for (int i = 0; i < mapData.length(); i++) {
                MapCell cell = this.addCell(mapData.getJSONObject(i));
                this.width = Math.max(this.width, cell.coords.first + 1);
                this.height = Math.max(this.height, cell.coords.second + 1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON parse failed");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON load failed bad encoding");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("JSON load failed");
        }

        console.sendMessage("map loaded " + this.width + ":" + this.height);
        this.renderMap();
    }

    private MapCell addCell(JSONObject jsonObject) throws JSONException {
        MapCell cell = new MapCell(jsonObject);
        this.console.sendMessage("add cell " + cell.coords.first + ":" + cell.coords.second);
        this.cells.put(cell.coords, cell);
        return cell;
    }

    private void renderMap() {
        int pixelCellSize = 16;
        Bitmap render = Bitmap.createBitmap(
                pixelCellSize * this.width,
                pixelCellSize * this.height,
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(render);
        Paint p = new Paint();
        p.setColor(Color.RED);
        //c.PrimitiveLine(0, 0, render.getScreenWidth() - 1, 0, p);
        //c.PrimitiveLine(render.getScreenWidth()- 1, 0, render.getScreenWidth()- 1, render.getScreenHeight(), p);
        //c.PrimitiveLine(render.getScreenWidth()- 1, render.getScreenHeight() - 1, 0, render.getScreenHeight(), p);
        //c.PrimitiveLine(0, render.getScreenHeight() - 1, 0, 0, p);

        Iterator itr = cells.entrySet().iterator();
        while (itr.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) itr.next();
            MapCell cell = (MapCell) pair.getValue();
            c.drawRect(
                    cell.coords.first * pixelCellSize,
                    cell.coords.second * pixelCellSize,
                    (cell.coords.first + 1) * pixelCellSize,
                    (cell.coords.second + 1) * pixelCellSize,
                    p
            );
        }

        this.loader.addTexture("render/map", render);
        this.console.sendMessage("map rendered");
    }

}
