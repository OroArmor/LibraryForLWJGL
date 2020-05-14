package com.oroarmor.app;

import com.oroarmor.core.openal.AudioMaster;
import com.oroarmor.core.openal.AudioSource;

public class AudioTest {

	public static void main(String[] args) throws InterruptedException {
		AudioMaster.loadSound("./res/test.ogg", "test");

		AudioSource source = new AudioSource();
		source.playSound(AudioMaster.getSound("test"));

		Thread.sleep(1000);

	}

}
