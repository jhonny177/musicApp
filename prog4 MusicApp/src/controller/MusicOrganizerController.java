package controller;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import model.Album;
import model.AlbumCaretaker;
import model.MusicOrganizerButtonPanel;
import model.SoundClip;
import model.SoundClipLoader;
import view.MusicOrganizerWindow;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private Album root;
	private Album album;
	private AlbumCaretaker care;
	private MusicOrganizerButtonPanel buttonPanel;

	
	
	public MusicOrganizerController() {
		
		// Create the root album for all sound clips
		root = new Album("All Sound Clips");
		
		// Create the View in Model-View-Controller
		view = new MusicOrganizerWindow(this);
		
		// Create the blocking queue
		queue = new SoundClipBlockingQueue();
		
		// Create a separate thread for the sound clip player and start it
		(new Thread(new SoundClipPlayer(queue))).start();
		
		care = new AlbumCaretaker();
		
		buttonPanel = new MusicOrganizerButtonPanel(this,view);
	}

	/**
	 * Load the sound clips found in all subfolders of a path on disk. If path is not
	 * an actual folder on disk, has no effect.
	 */
	public Set<SoundClip> loadSoundClips(String path) {
		Set<SoundClip> clips = SoundClipLoader.loadSoundClips(path);
		root.addAllSongs(clips);
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
		Album al = view.getSelectedAlbum();
		if (al != null) {
			String name = view.promptForAlbumName();
			if (name != null && al != null) {
				Album a = new Album(name);
				album = a;
				care.saveUndoState(album);
				al.addSubAlbum(a);
				view.onAlbumAdded(a);
			}
		} else {
			System.out.println("Please select a parent album for your new subalbum");
		}
		
	}
	
	/**
	 * Removes an album from the Music Organizer
	 */
	public void deleteAlbum(){
		Album a = view.getSelectedAlbum();
		if (a != null) {
			care.saveUndoState(a);
			a.getParentAlbum().deleteSubAlbum(a);
			view.onAlbumRemoved(a);
		}

	}
	/**
	 * Adds sound clips to an album
	 */
	public void addSoundClips(){
		Album a = view.getSelectedAlbum();
		if (a != null) {
			care.saveUndoState(a);
			Set<SoundClip> s = view.getSelectedSoundClips();
			a.addAllSongs(s);
		}
	}
	
	/**
	 * Removes sound clips from an album
	 */
	public void removeSoundClips() {
		Set<SoundClip> s = view.getSelectedSoundClips();
		Album a = view.getSelectedAlbum();
		if (s != null && a != null) {
			care.saveUndoState(a);
			a.removeAllSongs(s);
			view.onClipsUpdated();
		}
	}
	
	/**
	 * Puts the selected sound clips on the queue and lets
	 * the sound clip player thread play them. Essentially, when
	 * this method is called, the selected sound clips in the 
	 * SoundClipTable are played.
	 */
	public void playSoundClips() {
		Iterator<SoundClip> i = view.getSelectedSoundClips().iterator();
		while (i.hasNext()) {
			SoundClip s = i.next();
			i.remove();
			queue.enqueue(s);
		}
	}
	/**
	 * undo last change in application
	 */
	public void undoChange() {
		Album a = care.undo();
		if (a.equals(view.getSelectedAlbum())) {
			try {
				view.onClipsUpdated();
			} catch (Exception e) {
			}
		} else {
			try {
				view.onAlbumRemoved(a);
				view.onAlbumAdded(a);
				a.listSubAlbums().forEach(T -> view.onAlbumAdded(T));
			} catch (Exception e) {
			}
		}
	}
	/**
	 * redo last undo change in application
	 */
	public void redoChange() {
		Album a = care.redo();
		if (a.equals(view.getSelectedAlbum())) {
			try {
				view.onClipsUpdated();
			} catch (Exception e) {
			}
		} else {
			try {
				view.onAlbumRemoved(a);
				view.onAlbumAdded(a);
				a.listSubAlbums().forEach(T -> view.onAlbumAdded(T));
			} catch (Exception e) {
			}
		}


	}
	public void flagClip() {
		
	}
	public void favouriteClip() {
		
	}
	public AlbumCaretaker getCare() {
		return care;
	}
	
	
}
