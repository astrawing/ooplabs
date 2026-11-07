interface MediaPlayer {
    void play(String filename);
}
 
class AdvancedVideoPlayer {
    public void playVideo(String filename) {
        System.out.println("Playing video " + filename);
    }
}
 
class VideoPlayerAdapter implements MediaPlayer {
    private final AdvancedVideoPlayer videoPlayer;
 
    public VideoPlayerAdapter() {
        this.videoPlayer = new AdvancedVideoPlayer();
    }
 
    @Override
    public void play(String filename) {
        videoPlayer.playVideo(filename);
    }
}
 
public class MainMedia {
    public static void main(String[] args) {
        MediaPlayer player = new VideoPlayerAdapter();
        player.play("movie.mp4");
    }
}
 