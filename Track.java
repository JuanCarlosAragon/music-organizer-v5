/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    //Llevar la cuenta de reproducciones
    private int playCount;
    //Puntua la cancion de 1 a 10
    private int points;
    
    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title, String filename)
    {
        setDetails(artist, title, filename);
        playCount = 0;
        points = 0;
    }
    
    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", filename);
        playCount = 0;
    }
    
    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }
    
    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }
        
    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        return artist + ": " + title + "  (file: " + filename + ")\nPuntuación: " + points;
    }
    
    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, String filename)
    {
        this.artist = artist;
        this.title = title;
        this.filename = filename;
    }
    /**
     * Resetea el contador
     */
    public void resetCount(){
        playCount = 0;
    }
    /**
     * Incrementa el contador
     */
    public void incrementPlayCount(){
        playCount++;
    }
    /**
     * Retorna el valor del contador
     */
    public int getPlayCount(){
        return playCount;
    }
    /**
     * Fija los puntos asignados a la cancion
     */
    public void setPoints(int points){
        if((points >= 0) && (points <= 10)){
            this.points = points;
        }
        else if(points > 10){
            this.points = 10;
        }
    }
    /**
     * Devuelve el valor de puntos
     */
    public int getPoints(){
        return points;
    }
}
