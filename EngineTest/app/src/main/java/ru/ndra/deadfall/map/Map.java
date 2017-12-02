package ru.ndra.deadfall.map;

import android.content.Context;
import android.util.Pair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import ru.ndra.deadfall.console.ConsoleService;

/**
 * Класс для работы с картой
 */
public class Map {

    private final Context context;
    private final ConsoleService console;
    private HashMap<Pair<Integer, Integer>, MapCell> cells = new HashMap<>();

    public int width;

    public int height;

    public Map(Context context, ConsoleService console) {
        this.context = context;
        this.console = console;
        this.loadData();
        console.sendMessage("map loading");
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

            int width = 0;
            int height = 0;

            // Создаем ячейки карты
            JSONArray mapData = obj.getJSONArray("cells");
            for (int i = 0; i < mapData.length(); i++) {
                MapCell cell = this.addCell(mapData.getJSONObject(i));
                // width = Math.max(width, cell.x + 1);
                //   height = Math.max(height, cell.y + 1);
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

        //renderMap();

    }

    private MapCell addCell(JSONObject jsonObject) throws JSONException {
        MapCell cell = new MapCell(jsonObject);
        this.console.sendMessage("add cell " + cell.coords.first + ":" + cell.coords.second);
        this.cells.put(cell.coords, cell);
        return cell;
    }

    private void renderMap() {
      /*  int pixelCellSize = 16;
        Bitmap render = Bitmap.createBitmap(pixelCellSize * width, pixelCellSize * height, Bitmap.Config.ARGB_8888);
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
            c.drawRect(cell.x * pixelCellSize, cell.y * pixelCellSize, (cell.x + 1) * pixelCellSize, (cell.y + 1) * pixelCellSize, p);
        }

        game.loader.addTexture("render/map", render); */
    }

}
