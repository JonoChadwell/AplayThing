package jono;

import java.util.ArrayList;
import java.util.List;

public class Song {

   private double noteLength = 0.3;
   private double notePause = 0.05;
   private double fadeRate = 0.1;
   private double baseVolume[] = { 0.4 };

   private PitchGenerator gen;

   private List<Playable> notes;

   public Song(PitchGenerator gen) {
      this.gen = gen;
      notes = new ArrayList<>();
   }

   public void populatePlayer(Player player) {
      for (Playable p : notes) {
         player.addSound(p);
      }
   }

   public void addHalf(Pitch n, double pos) {
      notes.add(new FadedPlayable(gen.getPitch(n, baseVolume), pos * noteLength, (pos + 0.5) * noteLength - notePause,
            fadeRate));
   }

   public void addNote(Pitch n, double pos) {
      notes.add(new FadedPlayable(gen.getPitch(n, baseVolume), pos * noteLength, (pos + 1) * noteLength - notePause,
            fadeRate));
   }

   public void addDouble(Pitch n, double pos) {
      notes.add(new FadedPlayable(gen.getPitch(n, baseVolume), pos * noteLength, (pos + 2) * noteLength - notePause,
            fadeRate));
   }

   public void addQuad(Pitch n, double pos) {
      notes.add(new FadedPlayable(gen.getPitch(n, baseVolume), pos * noteLength, (pos + 4) * noteLength - notePause,
            fadeRate));
   }

}
