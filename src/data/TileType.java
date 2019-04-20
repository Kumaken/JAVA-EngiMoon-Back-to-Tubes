package data;

public enum TileType {

    GrassTile("grasstile", true), GrassGrown("grass", true),
    BarnTile("dirt", true), CoopTile("cooptile", true),
    Dirt("dirt", true), DirtP("dirtP", true);

    String textureName;
    boolean buildable;

    TileType(String _textureName, boolean _buildable){
        this.textureName = _textureName;
        this.buildable = _buildable;
    }
}
