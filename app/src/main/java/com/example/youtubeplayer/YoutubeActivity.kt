package com.example.youtubeplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID="9HDEHj2yzew"
const val YOUTUBE_PLAYLIST_ID="PLNrotoZZ8BaoXT_LJuwEyESQlctWNDCwD"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_youtube)
        val layout=layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

//        val button1= Button(this)
//        button1.layoutParams=ConstraintLayout.LayoutParams(600, 180)
//        button1.text="Button"
//        layout.addView(button1)

        val playerView=YouTubePlayerView(this)
        playerView.layoutParams=ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)


    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)
        if(!wasRestored){
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        } else{
            youTubePlayer?.play()
        }

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youTubeInitializationResult: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE=0
        if(youTubeInitializationResult?.isUserRecoverableError==true){
            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
        }
        else{
            val errorMsg="There was an error initializing the YouTubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
        }
    }
    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onSeekTo(p0: Int) {

        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Video is playing now", Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {

        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video has stopped", Toast.LENGTH_SHORT).show()
        }
    }
    private val playerStateChangeListener=object: YouTubePlayer.PlayerStateChangeListener{
        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Klikni i bice lik bogat", Toast.LENGTH_SHORT).show()
        }

        override fun onLoading() {

        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video has started", Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {

        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Video has ended", Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }
    }
}
