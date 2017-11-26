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

        String json = this.loadJSONFromAsset("graphics/map/" + name + "/config.json");

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray backgroundArray = obj.getJSONObject("scene")
                    .getJSONArray("background");

            for (int i = 0; i < backgroundArray.length(); i++) {
                JSONObject backgroundObject = backgroundArray.getJSONObject(i);
                backgroundScene.addParallax(
                        "map/" + name + "/" + backgroundObject.getString("texture"),
                        (float) backgroundObject.getDouble("height"),
                        (float) backgroundObject.getDouble("bottom"),
                        (float) backgroundObject.getDouble("speed"),
                        0
                );
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

       /* backgroundScene.addParallax("map/foothills/plane4.png", 200 * 3, 500, .1125f, 0);
        background.addParallax("map/foothills/plane3.png", 108 * 3, 400, .1875f, 0, false);
        background.addParallax("map/foothills/plane2.png", 69 * 3, 370, .375f, 0, false);

        background.addParallax("map/foothills/plane1.png", 145 * 3, 0, .625f, 0, false);
        background.addParallax("map/foothills/plane5.png", 74 * 3, 0, 1, 0, false);

        foreground.addParallax("map/foothills/plane6.png", 56 * 3, -50, 1.875f, 0, true);

        // Туман
        foreground.addParallax("map/weather/myst1.png", 56 * 3, 150, 1.1f, 10, true);
        foreground.addParallax("map/weather/myst1.png", 56 * 7, 150, 1.7f, -3, true); */

        //  foreground.addParallax("map/weather/myst1.png", 56 * 3, 150, 1.1f, 10, true);
        //  foreground.addParallax("map/weather/myst1.png", 56 * 7, 150, 1.7f, -3, true);

        //  this.createBog(background, foreground);

    }

    public void fillLayers() {
        
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

    public String loadJSONFromAsset(String asset) {
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
