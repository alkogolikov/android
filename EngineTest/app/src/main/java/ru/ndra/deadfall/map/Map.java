package ru.ndra.deadfall.map;

import android.content.Context;
import android.util.Pair;

import org.json.JSONObject;

import java.util.HashMap;

import ru.ndra.deadfall.Game;

/**
 * Класс для работы с картой
 */
public class Map {

    private HashMap<Pair<Integer,Integer>, MapCell> cells = new HashMap<>();

    public int width;

    public int height;

    public Map(Context context) {
        //loadData();
    }

    // @todo сделать загрузку данных в трэде
    public void loadData() {
       /* try {

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
        */
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
