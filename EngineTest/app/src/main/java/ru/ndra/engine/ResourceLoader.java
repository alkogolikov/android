package ru.ndra.engine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ResourceLoader {

    private Context context;
    private LoadThread thread;
    private ArrayList<String> bitmapsToLoad = new ArrayList<String>();
    private HashMap<String, Integer> textures = new HashMap<String, Integer>();
    private HashMap<String, Bitmap> texturesToCreate = new HashMap<String, Bitmap>();
    private HashMap<String, Point> textureSize = new HashMap<>();

    public ResourceLoader(Game game) {
        this.context = game.context;
    }

    /**
     * Добавляет файл в список загрузки
     *
     * @param name
     */
    synchronized public void addTexture(String name) {
        synchronized (this) {
            if (bitmapsToLoad.contains(name)) {
                return;
            }
            if (textures.containsKey(name)) {
                return;
            }
            if (texturesToCreate.containsKey(name)) {
                return;
            }
            bitmapsToLoad.add(name);
            startThread();
        }
    }

    public void addTexture(String name, Bitmap bitmap) {
        synchronized (this) {
            if (bitmapsToLoad.contains(name)) {
                return;
            }
            if (textures.containsKey(name)) {
                return;
            }
            if (textures.containsKey(name)) {
                return;
            }
            texturesToCreate.put(name, bitmap);
        }
    }

    /**
     * todo заменить run() на start()
     */
    public void startThread() {
        synchronized (this) {
            if (thread == null) {
                thread = new LoadThread();
                thread.start();
            }
        }
    }

    public int getTexture(String name) {
        if(!textures.containsKey(name)) {
            throw new RuntimeException("Cannot get bitmap: " + name);
        }
        return textures.get(name);
    }

    public Point textureSize(String name) {
        return this.textureSize.get(name);
    }

    public boolean isLoaded() {
        synchronized(this) {
            if (!bitmapsToLoad.isEmpty()) {
                return false;
            }
            if (!texturesToCreate.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод, который вызывается в трэде OpenGL для создания тексур
     * (Мы не можем моздавать текстуры в других трэдах)
     */
    synchronized public void inOpenglThread() {
        while (true) {

            Map.Entry<String, Bitmap> entry;
            synchronized(this) {
                if (texturesToCreate.isEmpty()) {
                    break;
                }
                entry = texturesToCreate.entrySet().iterator().next();
            }
            String name = (String) entry.getKey();
            Bitmap bitmap = (Bitmap) entry.getValue();

            final int[] textureIds = new int[1];
            GLES20.glGenTextures(1, textureIds, 0);

            if (textureIds[0] == 0) {
                throw new RuntimeException("Texture allocate failed");
            }

            // настройка объекта текстуры
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureIds[0]);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);

            textureSize.put(name, new Point(bitmap.getWidth(), bitmap.getHeight()));

            bitmap.recycle();

            // сброс target
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

            synchronized(this) {
                textures.put(name, textureIds[0]);
                texturesToCreate.remove(name);
            }

            Log.d("xxx", "Loading in opengl thread " + name);
        }

    }

    class LoadThread extends Thread {

        @Override
        public void run() {

            Log.d("xxx", "load thread started");

            // Загружаем картинки
            Iterator<String> itr = bitmapsToLoad.iterator();

            while(!bitmapsToLoad.isEmpty()) {
                String name = bitmapsToLoad.get(0);
                String filePath = "graphics/" + name;
                try {

                    AssetManager assetManager = context.getAssets();
                    InputStream istr;
                    Bitmap bmp = null;
                    istr = assetManager.open(filePath);
                    bmp = BitmapFactory.decodeStream(istr);

                    synchronized (ResourceLoader.this) {
                        texturesToCreate.put(name, bmp);
                        bitmapsToLoad.remove(0);
                    }

                } catch (IOException e) {
                    throw new RuntimeException("Cannot load bitmap " + filePath);
                }
            }

            thread = null;
            Log.d("xxx", "load thread finished");
        }


    }

}
