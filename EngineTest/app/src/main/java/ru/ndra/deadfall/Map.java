package ru.ndra.deadfall;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Класс для работы с картой
 */
class Map {

    private Game game;

    private HashMap<String, MapCell> cells = new HashMap<String, MapCell>();

    public int width;

    public int height;

    public Map(Game game) {
        this.game = game;
        loadData();
    }

    // @todo сделать загрузку данных в трэде
    public void loadData() {
        try {

            // Загружаем карту из JSON
            InputStream is = game.context.getAssets().open("data/map.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject obj = new JSONObject(json);

            int width = 0;
            int height = 0;

            // Создаем ячейки карты
            JSONArray mapData = obj.getJSONArray("cells");
            for (int i = 0; i < mapData.length(); i++) {
                MapCell cell = addCell(mapData.getJSONObject(i));
                width = Math.max(width, cell.x + 1);
                height = Math.max(height, cell.y + 1);
            }

            this.width = width;
            this.height = height;

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

        renderMap();

    }

    private void renderMap() {
        int pixelCellSize = 16;
        Bitmap render = Bitmap.createBitmap(pixelCellSize * width, pixelCellSize * height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(render);
        Paint p = new Paint();
        p.setColor(Color.RED);
        //c.DrawLine(0, 0, render.getWidth() - 1, 0, p);
        //c.DrawLine(render.getWidth()- 1, 0, render.getWidth()- 1, render.getHeight(), p);
        //c.DrawLine(render.getWidth()- 1, render.getHeight() - 1, 0, render.getHeight(), p);
        //c.DrawLine(0, render.getHeight() - 1, 0, 0, p);

        Iterator itr = cells.entrySet().iterator();
        while (itr.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry) itr.next();
            MapCell cell = (MapCell) pair.getValue();
            c.drawRect(cell.x * pixelCellSize, cell.y * pixelCellSize, (cell.x + 1) * pixelCellSize, (cell.y + 1) * pixelCellSize, p);
        }

        game.loader.addTexture("render/map", render);
    }

    /**
     * Добавляет ячейку в карту
     * Используется загрузчиком карты
     */
    private MapCell addCell(JSONObject data) {
        MapCell cell = new MapCell(this, data);
        String key = cell.x + ":" + cell.y;
        cells.put(key, cell);
        return cell;
    }

    /**
     * Возвращает ячейку карты с заданными координатами
     */
    public MapCell cell(int x, int y) {
        MapCell cell = cells.get(x + ":" + y);
        if (cell == null) {
            cell = new MapCell(this, x, y);
        }
        return cell;
    }

}
