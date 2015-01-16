import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;
    // Interruptor de testigo de si hay algo en reproducción
    private boolean isPlaying;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer(String origenArchivos)
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary(origenArchivos);
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
        isPlaying = false;
    }
    
    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }
    
    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }
    
    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        if(indexValid(index)) {
            if(isPlaying){
                System.out.println("ERROR: El reproductor ya está en funcionamiento");
            }
            else{
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
                track.incrementPlayCount();
                System.out.println("Reproducciones realizadas: " + track.getPlayCount());
                isPlaying = true;
            }
        }
    }
    
    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }
    
    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }
    
    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }
    
    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }
    
    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }
    
    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        if(tracks.size() > 0) {
            if(isPlaying){
                System.out.println("Error: El reproductor ya está en funcionamiento");
            }
            else{
                player.startPlaying(tracks.get(0).getFilename());
                Track track = tracks.get(0);
                track.incrementPlayCount();
                System.out.println("Reproducciones realizadas: " + track.getPlayCount());
                isPlaying = true;
            }
        }
    }
    
    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
        isPlaying = false;
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;
        
        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }
    
    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }
    /**
     * Muestra por pantalla las canciones que contienen el titulo pasado como parametro
     */
    public void findIntTitle(String titulo){
        for(Track cancion : tracks) {
            if(cancion.getTitle().contains(titulo)){
                System.out.println(cancion.getTitle());
            }
        }
    }
    /**
     * Fija los puntos a una determinada cancion
     */
    public void setPoints(String titleOfTrack, int points){
        for(Track track : tracks){
            if(track.getTitle().contains(titleOfTrack)){
                track.setPoints(points);
            }
        }
        
    }
    /**
     * informa por pantalla si hay algo reproduciendose
     */
    public void isPlayingInformation(){
        if(isPlaying){
            System.out.println("El reproductor está en funcionamiento");
        }
        else{
            System.out.println("El reproductor no está reproduciendo");
        }
    }
    /**
     * Lista por pantalla todos los temas usando un elemento de la clase Iterator
     */
    public void listAllTrackWithIterator(){
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext()){
            Track track = it.next();
            System.out.println(track.getDetails());
        }
    }
    /**
     * Borra del organizador la cancion que contetnga un determinado artista
     */
    public void removeByArtist(String artist){
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext()){
            Track track = it.next();
            if(track.getArtist().contains(artist)){
                it.remove();
            }
        }
    }
    /**
     * Reproduce una cancion al azar
     */
    public void playRandom(){
        Random rnd = new Random();
        int numeroAleatorio = (int)((rnd.nextDouble()*(tracks.size()-1)));
        playTrack(numeroAleatorio);
    }
    /**
     * Reproduce los primeros segundos de todas las canciones en orden aleatorio
     * pero solo una vez
     */
    public void playShuffle(){
        Collections.shuffle(tracks);
        for(Track track : tracks){
            player.playSample(track.getFilename());
        }
    }
    /**
     * Reproduce los primeros segundos de todas las canciones en orden aleatorio 
     * pero solo una vez
     */
    public void playShuffle2(){
        //Inicializamos el Random
        Random rnd = new Random();
        //Inicializamos el ArrayList y lo copiamos de tracks
        ArrayList<Track> bateriaCanciones = new ArrayList<Track>();
        bateriaCanciones = (ArrayList)tracks.clone();
        //Mientras halla canciones en el Array y el reproductor esté parado.
        while((bateriaCanciones.size()!= 0) && (isPlaying == false))
        {
            int numeroAleatorio = (int)((rnd.nextDouble()*(bateriaCanciones.size()-1)));            
            Track track = bateriaCanciones.get(numeroAleatorio);
            player.playSample(track.getFilename());
            while(player.isPlaying()){
                isPlaying = true;
            }
            isPlaying = false;
            bateriaCanciones.remove(numeroAleatorio);
        }
    }
}
