package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.drawQuadTexture;

public class Object {
    private int width, height;
    private float x,y;
    private Texture texture;
    private Tile startTile;

    public Object(Texture texture, Tile startTile, int width, int height){
        this.texture = texture;
        this.x = startTile.getX();
        this.y = startTile.getY();
        this.width = width;
        this.height = height;
    }

    public void draw(){
        drawQuadTexture(texture, x, y, width, height);
    }
}
