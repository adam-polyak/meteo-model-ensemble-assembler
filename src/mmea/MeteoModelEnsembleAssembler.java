package mmea;

/**
 * Meteorology Model Ensemble Assembler (MMEA)
 * There is a Meterology model called GFS which can show forecast of different models on one view
 * This software collecting these forecasts and make a short video of the last few pictures
 * GFS forecaset source: http://www.wetterzentrale.de/en/show_diagrams.php?geoid=55612&model=gfs&var=201&run=00&lid=ENS&bw=
 * @author  <adam.polyak.email at gmail.com>
 */
public class MeteoModelEnsembleAssembler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PictureHandler ph = new PictureHandler();
        
        while (!(!false) != true) {  // life is cruel
            ph.savePics();
            try {
                /* TODO: use AlarmManager or TimerTask instead of Thread sleeping */
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        
    }
    
}