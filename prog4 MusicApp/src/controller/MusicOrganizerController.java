package controller;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import javax.swing.JOptionPane;

import model.Album;

import model.RegularAlbum;
import model.SearchBasedAlbum;

import model.AlbumCaretaker;
import model.MusicOrganizerButtonPanel;
import model.SoundClip;
import model.SoundClipLoader;
import view.MusicOrganizerWindow;

public class MusicOrganizerController {

	private MusicOrganizerWindow view;
	private SoundClipBlockingQueue queue;
	private RegularAlbum root;
	private RegularAlbum album;
	private SearchBasedAlbum flagAlbum;
	private SearchBasedAlbum greatAlbum;
	private AlbumCaretaker care;
	private MusicOrganizerButtonPanel buttonPanel;

	
	
	public MusicOrganizerController() {
		
		// Create the root album for all sound clips
		root = new RegularAlbum("All Sound Clips");
		flagAlbum = new SearchBasedAlbum("Flagged Sound Clips",root);
		greatAlbum = new SearchBasedAlbum("Great Sound Clips",root);
		
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
	public RegularAlbum getRootAlbum(){
		return root;
	}

	/**
	 * Returns the flag album
	 */
	public SearchBasedAlbum getFlagAlbum(){
		return flagAlbum;
	}

	/**
	 * Returns the great album
	 */
	public SearchBasedAlbum getGreatAlbum(){
		return greatAlbum;
	}
	
	/**
	 * Adds an album to the Music Organizer
	 */
	public void addNewAlbum(){ 
		RegularAlbum al = (RegularAlbum) view.getSelectedAlbum();
		if (al != null) {
			String name = view.promptForAlbumName();
			if (name != null && al != null) {
				RegularAlbum a = new RegularAlbum(name);
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
		RegularAlbum a = (RegularAlbum) view.getSelectedAlbum();
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
		RegularAlbum a = (RegularAlbum) view.getSelectedAlbum();
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
		RegularAlbum a = (RegularAlbum) view.getSelectedAlbum();
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
		RegularAlbum a = care.undo();
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
		RegularAlbum a = care.redo();
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

		try {
		
		Iterator<SoundClip> i = view.getSelectedSoundClips().iterator();
		RegularAlbum a = (RegularAlbum) view.getSelectedAlbum();
		while(i.hasNext()) {
			SoundClip s = i.next();
			care.saveUndoState(a);
			if(s.getFlagged()==false) {
				s.setFlagged(true);
				flagAlbum.addSong(s);

			}

			

			else if(s.getFlagged()==true) {
				s.setFlagged(false);
				flagAlbum.removeSong(s);
			}

		}
		view.onClipsUpdated();
		}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "No clip selected");
			}
	}
	public void favouriteClip() {
		try {
			Integer[] options = {1, 2, 3, 4, 5};
	        int score = (Integer)JOptionPane.showInputDialog(null, "Give scor to clip:",
	                "Numbers", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
	        Iterator<SoundClip> i = view.getSelectedSoundClips().iterator();
			while(i.hasNext()) {
				SoundClip s = i.next();
				s.setScore(score);
			}
			view.onClipsUpdated();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No clip selected");
		}
	}
	public AlbumCaretaker getCare() {
		return care;
	}
	
	
}
