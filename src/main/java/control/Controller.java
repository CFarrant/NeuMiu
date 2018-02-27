package control;

import enums.VolumeLevel;

public class Controller {

	private boolean mute;
	private VolumeLevel volume = VolumeLevel.MEDIUM;

	public Controller() {

	}

	public void run() {

	}

	public boolean isMute() {
		return mute;
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public VolumeLevel getVolume() {
		return volume;
	}

	public void setVolume(VolumeLevel volume) {
		this.volume = volume;
	}

	public void play() {

	}

	public void nextSong() {

	}

	public void prevSong() {

	}

	public void pause() {

	}

	public void stop() {

	}

	public void shuffel() {

	}

	public void repeat() {

	}

	public int volume(int vol) {
		return vol;
	}

	public void mute(boolean mute) {
		if (mute == true) {

		} else {

		}
	}
}
