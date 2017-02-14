package jono;
import java.util.function.Consumer;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.ugens.Gain;

public class AplayThing {
   private static final int SAMPLE_RATE = 44100;
   
   private static int OCTAVE = 0;
   private static double BASE_AMPLITUDE[] = {0.4};
   private static Waveform WAVEFORM = Waveform.SINE;
   
   private static Player player;
   
   public static void main(String args[]) throws Exception {
      System.out.println("Tone Generator by Jono Chadwell");
      AudioContext ac;
      ac = new AudioContext();
      
      parseArgs(args);
      player = new Player(ac);
      
      PitchGenerator pg = new PitchGenerator(BASE_AMPLITUDE, OCTAVE, WAVEFORM);
      
      Song s = littleLamb(pg);
      
      s.populatePlayer(player);
      
      Gain g = new Gain(ac, 1, 0.1f);
      g.addInput(player);
      ac.out.addInput(g);
      ac.start();
   }
   
   private static void parseArgs(String args[]) {
      for (String s : args) {
         if (s.contains("help") || s.equals("-h") || s.equals("?") ) {
            System.out.println("Options:");
//            System.out.println("--seed= (-s=) seed for music");
//            System.out.println("--length= (-l=) note length (seconds)");
//            System.out.println("--pause= (-p=) break between notes");
//            System.out.println("--fade= (-f=) fade speed");
//            System.out.println("--node= (-n=) base note");
//            System.out.println("--chord= (-c=) chord type");
            System.out.println("--octave= (-o=) octave shift");
            System.out.println("--wave= (-w=) waveform (SINE, SQUARE, SAWTOOTH, TRIANGLE, BACKSAW)");
            System.out.println("--amplitude= (-a=) list of amplitudes for overtones");
         }
//         ifStarts((str) -> SEED = str.hashCode(), s, "-s=", "--seed=");
//         ifStarts((str) -> LEN = Double.valueOf(str), s, "-l=", "--length=");
//         ifStarts((str) -> PAUSE = Double.valueOf(str), s, "-p=", "--pause=");
//         ifStarts((str) -> FADE_RATE = Double.valueOf(str), s, "-f=", "--fade=");
//         ifStarts((str) -> BASE_NOTE = Pitch.valueOf(str), s, "-n=", "--note=");
//         ifStarts((str) -> BASE_CHORD = Chord.valueOf(str), s, "-c=", "--chord=");
         ifStarts((str) -> OCTAVE = Integer.valueOf(str), s, "-o=", "--octave=");
         ifStarts((str) -> WAVEFORM = Waveform.valueOf(str), s, "-w=", "--wave=");
         ifStarts((str) -> setAmplitude(str), s, "-a=", "--amplitude=");
      }
   }
   
   private static void setAmplitude(String str) {
      System.out.println("Setting amplitude A");
      String[] parts = str.split(",");
      double[] overtones = new double[parts.length];
      for (int i = 0; i < parts.length; i++) {
         overtones[i] = Double.valueOf(parts[i]);
      }
      System.out.println("Setting amplitude B");
      BASE_AMPLITUDE = overtones;
   }
   
   private static void ifStarts(Consumer<String> funct, String s, String... prefixes) {
      for (String prefix : prefixes) {
         if (s.startsWith(prefix)) {
            String rest = s.substring(prefix.length());
            funct.accept(rest);
         }
      }
   }
   
   // Mary Had a Little Lamb
   private static Song littleLamb(PitchGenerator pg) {
      Song s = new Song(pg);
      
      s.addNote(Pitch.E, 0);
      s.addNote(Pitch.D, 1);
      s.addNote(Pitch.C, 2);
      s.addNote(Pitch.D, 3);
      s.addNote(Pitch.E, 4);
      s.addNote(Pitch.E, 5);
      s.addDouble(Pitch.E, 6);
      
      s.addNote(Pitch.D, 8);
      s.addNote(Pitch.D, 9);
      s.addDouble(Pitch.D, 10);
      s.addNote(Pitch.E, 12);
      s.addNote(Pitch.G, 13);
      s.addDouble(Pitch.G, 14);
      
      s.addNote(Pitch.E, 16);
      s.addNote(Pitch.D, 17);
      s.addNote(Pitch.C, 18);
      s.addNote(Pitch.D, 19);
      s.addNote(Pitch.E, 20);
      s.addNote(Pitch.E, 21);
      s.addNote(Pitch.E, 22);
      s.addNote(Pitch.E, 23);
      
      s.addNote(Pitch.D, 24);
      s.addNote(Pitch.D, 25);
      s.addNote(Pitch.E, 26);
      s.addNote(Pitch.D, 27);
      s.addQuad(Pitch.C, 28);
      
      return s;
   }
   
   // The Saints go marching in
   private static Song saints(PitchGenerator pg) {
      Song s = new Song(pg);

      s.addNote(Pitch.C , 0);
      s.addNote(Pitch.E , 1);
      s.addNote(Pitch.F , 2);
      s.addNote(Pitch.G , 3);
      
      s.addNote(Pitch.C , 6);
      s.addNote(Pitch.E , 7);
      s.addNote(Pitch.F , 8);
      s.addNote(Pitch.G , 9);
      
      s.addNote(Pitch.C , 12);
      s.addNote(Pitch.E , 13);
      s.addNote(Pitch.F , 14);
      
      s.addNote(Pitch.G , 15);
      s.addNote(Pitch.E , 17);
      s.addNote(Pitch.C , 19);
      s.addNote(Pitch.E , 21);
      s.addNote(Pitch.D , 23);
      
      s.addNote(Pitch.E , 26);
      s.addNote(Pitch.E , 27);
      s.addNote(Pitch.D , 28);
      s.addNote(Pitch.C , 29);
      s.addNote(Pitch.C , 31);
      s.addNote(Pitch.E , 32);
      s.addNote(Pitch.G , 34);
      s.addNote(Pitch.G , 35);
      s.addNote(Pitch.G , 36);
      s.addNote(Pitch.F , 37);
      
      s.addNote(Pitch.E , 40);
      s.addNote(Pitch.F , 41);
      s.addNote(Pitch.G , 43);
      s.addNote(Pitch.E , 45);
      s.addNote(Pitch.C , 47);
      s.addNote(Pitch.D , 49);
      s.addNote(Pitch.C , 51);
      
      return s;
   }
}
