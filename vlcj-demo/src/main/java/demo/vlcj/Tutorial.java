package demo.vlcj;

import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Tutorial {

    private final JFrame frame;

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public static void main(String[] args) {
        Tutorial thisApp = new Tutorial(args);
    }

    public Tutorial(String[] args) {
//        MediaPlayerFactory factory = new MediaPlayerFactory();
        frame = new JFrame("My First Media Player");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // FIXME this is wrong in current tutorials also need to actually run this code to make sure // mark
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        frame.setContentPane(mediaPlayerComponent);
        frame.setVisible(true);
        String media = Thread.currentThread().getContextClassLoader().getResource("bug_saisie.mp4").getFile();
        System.out.println(media);

        mediaPlayerComponent.mediaPlayer().events().addMediaPlayerEventListener(
                new MediaPlayerEventAdapter() {

                    @Override
                    public void playing(MediaPlayer mediaPlayer) {
                    }

                    @Override
                    public void finished(MediaPlayer mediaPlayer) {
                        System.out.println("Finished");
                    }

                    @Override
                    public void error(MediaPlayer mediaPlayer) {
                    }
                }
        );
        mediaPlayerComponent.mediaPlayer().media().play(media);
    }

}
