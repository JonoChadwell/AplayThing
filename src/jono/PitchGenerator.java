package jono;

public class PitchGenerator {
   // twelfth root of two
   private static final double DIFF = 1.05946309435929526456182529494634170077920431749;
   private static final double A = 440.0;

   private final double[] baseAmplitude;
   private final int keyOctave;
   private final Waveform waveform;

   public PitchGenerator(double[] baseAmplitude, int keyOctave, Waveform waveform) {
      this.baseAmplitude = baseAmplitude;
      this.keyOctave = keyOctave;
      this.waveform = waveform;
   }

   public Playable getPitch(Pitch note) {
      return generatePitch(note.position, baseAmplitude, keyOctave);
   }

   public Playable getPitch(Pitch note, double[] amplitude) {
      return generatePitch(note.position, amplitude, keyOctave);
   }

   public Playable getPitch(Pitch note, int octave) {
      return generatePitch(note.position, baseAmplitude, octave);
   }

   public Playable getPitch(Pitch note, double[] amplitude, int octave) {
      return generatePitch(note.position, amplitude, octave);
   }

   private Playable generatePitch(int note, double amplitudes[], int octave) {
      
      return new OvertonedWave(Math.pow(2, octave) * Math.pow(DIFF, note) * A, waveform, amplitudes);
   }
}
