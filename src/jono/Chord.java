package jono;

   

public enum Chord {
   MAJOR(Chord.MAJOR_CHORD_ARR),
   MINOR(Chord.MINOR_CHORD_ARR);

   private static final int[] MINOR_CHORD_ARR = {0,2,3,5,7,8,10,12};
   private static final int[] MAJOR_CHORD_ARR = {0,2,4,5,7,9,11,12};

   private final int[] chord;

   private Chord(int[] chord) {
      this.chord = chord;
   }

   public int[] getChord() {
      return this.chord;
   }
}
