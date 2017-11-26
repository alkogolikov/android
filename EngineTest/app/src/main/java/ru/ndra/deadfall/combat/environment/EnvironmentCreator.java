package ru.ndra.deadfall.combat.environment;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class EnvironmentCreator {

    private Context context;

    public EnvironmentCreator(Context context) {
        this.context = context;
    }

    public void createEnveronment(
            String name,
            ParallaxScene backgroundScene,
            ParallaxScene foregroundScene
    ) {

        String json = this.loadStringFromAsset("graphics/map/" + name + "/config.json");

        try {
            JSONObject obj = new JSONObject(json);
            this.fillLayers(
                    name,
                    backgroundScene,
                    obj.getJSONObject("scene").getJSONArray("background")
            );
            this.fillLayers(
                    name,
                    foregroundScene,
                    obj.getJSONObject("scene").getJSONArray("foreground")
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        this.createMyst(backgroundScene, foregroundScene);
    }

    private void createMyst(
            ParallaxScene backgroundScene,
            ParallaxScene foregroundScene
    ) {
        foregroundScene.addParallax("map/weather/myst1.png", 56 * 3, 150, 1.1f, 10);
        foregroundScene.addParallax("map/weather/myst1.png", 56 * 7, 150, 1.7f, -3);
    }

    /**
     * Добавляет слои из JSON
     *
     * @param name
     * @param scene
     * @param backgroundArray
     * @throws JSONException
     */
    public void fillLayers(String name, ParallaxScene scene, JSONArray backgroundArray) throws JSONException {
        for (int i = 0; i < backgroundArray.length(); i++) {
            JSONObject backgroundObject = backgroundArray.getJSONObject(i);
            scene.addParallax(
                    "map/" + name + "/" + backgroundObject.getString("texture"),
                    (float) backgroundObject.getDouble("height"),
                    (float) backgroundObject.getDouble("bottom"),
                    (float) backgroundObject.getDouble("speed"),
                    0
            );
        }
    }

    public void createForest(ParallaxScene background, ParallaxScene foreground) {
     /*   background.addParallax("map/forest/plane1.png", 1000, 0, .1125f, 0, false);
        background.addParallax("map/forest/plane3.png", 1000, 0, .2f, 0, false);
        background.addParallax("map/forest/plane2.png", 1000, 0, .4f, 0, false);
        background.addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, 0, false);
        foreground.addParallax("map/foothills/plane6.png", 56 * 3, -50, 1.875f, 0, true); */
    }

    public void createBog(ParallaxScene background, ParallaxScene foreground) {
        /*background.addParallax("map/bog/plane1.png", 1000, 0, .1125f, 0, false);
        background.addParallax("map/bog/plane2.png", 1000, 0, .2f, 0, false);
        background.addParallax("map/bog/plane3.png", 1000, 0, .4f, 0, false);
        background.addParallax("map/bog/plane4.png", 1000, 0, 1, 0, false); */
    }

    /**
     * Загружает строку из ассетов
     *
     * @param asset
     * @return
     */
    public String loadStringFromAsset(String asset) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(asset);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
