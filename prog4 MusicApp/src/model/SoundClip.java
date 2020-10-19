package model;
import java.io.File;

/**
 * SoundClip is a class representing a digital
 * sound clip file on disk.
 */
public class SoundClip {

	private final File file;
	private boolean flagged = false;
	private Integer score;
	
	/**
	 * Make a SoundClip from a file.
	 * Requires file != null.
	 */
	public SoundClip(File file) {
		assert file != null;
		this.file = file;
	}

	public void setFlagged(boolean s) {
		flagged = s;
	}
	
	public boolean getFlagged() {
		return flagged;
	}
	
	public void setScore(int s) {
		assert(s <= 5 && s >= 0);
		score = s;
	}
	
	public int getScore() {
		return score;
	}
	/**
	 * @return the file containing this sound clip.
	 */
	public File getFile() {
		return file;
	}
	
	public String toString(){
		return file.getName() + ((flagged = true)? " ⚑" : "") + ((score != null)? " " + score : "");
	}
	
	@Override
	public boolean equals(Object obj) {
		return 
			obj instanceof SoundClip
			&& ((SoundClip)obj).file.equals(file);
	}
	
	@Override
	public int hashCode() {
		return file.hashCode();
	}
}
