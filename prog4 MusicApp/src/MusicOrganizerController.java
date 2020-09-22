import java.util.List;
import java.util.Set;

import app.Album;
import app.ManageAlbum;
import app.SoundClip;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album album;
	
	
	public MusicOrganizerController() {
		
		// Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		for(SoundClip s:clips) {
		root.addSong(s);	
		}
		return clips;
	}
	
	/**
	 * Returns the root album
	 */
	public Album getRootAlbum(){
		return root;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){ 
		String name = view.promptForAlbumName();
		if (name != null) {
			Album a = new Album(name);
			view.getSelectedAlbum().addSubAlbum(a);
			view.onAlbumAdded(a);
		}
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){
		Album a = view.getSelectedAlbum();
		a.getParentAlbum().deleteSubAlbum(a);
		view.onAlbumRemoved(a);
	}
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){
		Album a = view.getSelectedAlbum();
		List<SoundClip> s = view.getSelectedSoundClips();
		a.addAllSongs(s);
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips() {
		List<SoundClip> s = view.getSelectedSoundClips();
		view.getSelectedAlbum().removeAllSongs(s);
		view.onClipsUpdated();
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips(){
		List<SoundClip> l = view.getSelectedSoundClips();
		for(int i=0;i<l.size();i++)
			queue.enqueue(l.get(i));
	}
}
