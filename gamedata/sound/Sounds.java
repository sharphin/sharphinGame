package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sounds{
    private static Clip clip[] = new Clip[4];
    private static AudioInputStream sound[] = new AudioInputStream[4];

    private static Sequencer sequencer1 = null;
    private static Sequencer sequencer2 = null;

    public Sounds() {
        sound_set();
        sound_effect_set();
    }
    public void sound_set() {
        try {
            sequencer1 = MidiSystem.getSequencer();
            sequencer1.open();
            sequencer2 = MidiSystem.getSequencer();
            sequencer2.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        try {
            File file1 = new File("sound/BGM/Search.mid");
            File file2 = new File("sound/BGM/Battle.mid");
            Sequence sequence1 = MidiSystem.getSequence(file1);
            sequencer1.setSequence(sequence1);
            sequencer1.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

            Sequence sequence2 = MidiSystem.getSequence(file2);
            sequencer2.setSequence(sequence2);
            sequencer2.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);

        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    public static final void sound_chage(boolean search) {
        if(search) {
            sequencer2.stop();
            sequencer1.start();
        } else {
            sequencer1.stop();
            sequencer2.start();
        }
    }
    public void sound_effect_set() {
        try {
            sound[0] = AudioSystem.getAudioInputStream(new File("sound/BGM/menu.wav"));
            sound[1] = AudioSystem.getAudioInputStream(new File("sound/BGM/decide.wav"));
            sound[2] = AudioSystem.getAudioInputStream(new File("sound/BGM/stairs.wav"));
            sound[3] = AudioSystem.getAudioInputStream(new File("sound/BGM/dialogue.wav"));
            for(int i = 0; i < 4; i++) {
                clip[i] = AudioSystem.getClip();
                clip[i].open(sound[i]);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void play_sound(int state) {
        clip[state].setFramePosition(1500);
        clip[state].start();
        try{
            Thread.sleep(50);
        } catch(InterruptedException e) {}
        if(state != 3) clip[state].stop();
    }
}