package helpers;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Class for rendering the GUI
 */
public class Artist {
    public static final int WIDTH = 1366, HEIGHT = 960;

    /**
     * Method to initialize OpenGL
     */
    public static void beginSession(){
            //setup screen:
            Display.setTitle("Java moon");
            try{
                Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
                Display.create();
            } catch (LWJGLException e){ //in case something goes wrong pls don't mess my comp.
                e.printStackTrace();
            }
            //setup opengl:
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, WIDTH, HEIGHT, 0, 1, -1 );
            glMatrixMode(GL_MODELVIEW);
            //enable textures:
            glEnable(GL_TEXTURE_2D);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Method to draw the texture of objects on map
     * @param tex
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void drawQuadTexture(Texture tex, float x, float y, float width, float height){
        tex.bind();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(0,0);
        glTexCoord2f(1,0);
        glVertex2f(width, 0);
        glTexCoord2f(1,1);
        glVertex2f(width, height);
        glTexCoord2f(0,1);
        glVertex2f(0,height);
        glEnd();
        //load identity must always be the last to avoid crazy disco updating mania
        glLoadIdentity();
    }

    /**
     * Method to load the texture for objects on map
     * @param path of file
     * @param filetype
     * @return
     */
    public static Texture loadTexture(String path, String filetype){
        Texture tex = null;
        InputStream in = ResourceLoader.getResourceAsStream(path);
        try {
            tex = TextureLoader.getTexture(filetype, in);
        }catch (IOException e){
            e.printStackTrace();
        }
        return tex;
    }

    /**
     * Method to return texture of objects on map
     * @param name
     * @return texture of object png file
     */
    public static Texture quickLoad (String name){
        Texture tex = null;
        tex = loadTexture("res/" + name + ".png", "PNG" );
        return tex;
    }
}

