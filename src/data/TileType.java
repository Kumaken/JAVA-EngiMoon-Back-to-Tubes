package data;

/**
 * Enum to load the land texture
 */
public enum TileType {

    GrassTile("grasstile", true), GrassGrown("grass", true),
    BarnTile("dirt", true), CoopTile("cooptile", true);

    String textureName;
    boolean buildable;

    /**
     * assign name of texture
     * @param _textureName
     * @param _buildable
     */
    TileType(String _textureName, boolean _buildable){
        this.textureName = _textureName;
        this.buildable = _buildable;
    }
}
