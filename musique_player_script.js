// Tutoriel used : https://www.geeksforgeeks.org/create-a-music-player-using-javascript/

let now_playing = document.querySelector(".now-playing");
let track_art = document.querySelector(".track-art");
let track_name = document.querySelector(".track-name");
let track_artist = document.querySelector(".track-artist");
 
let playpause_btn = document.querySelector(".playpause-track");
let next_btn = document.querySelector(".next-track");
let prev_btn = document.querySelector(".prev-track");
 
let seek_slider = document.querySelector(".seek_slider");
let volume_slider = document.querySelector(".volume_slider");
let curr_time = document.querySelector(".current-time");
let total_duration = document.querySelector(".total-duration");

// Specify globally used values
let track_index = 0;
let isPlaying = false;
let updateTimer;
 
//Url needed 
const urlApi = "http://localhost:8080"
const urlMusicListenable = urlApi + "/music/music_listenable"
const urlGetMusicFile = urlApi + "/music/music_file"
// Create the audio element for the player
let curr_track = document.createElement('audio');


async function getMusicFile(idMusic) {
  //We get the music with the id
  let response = await fetch(urlGetMusicFile +"/"+ idMusic,{
    method: 'GET', // Spécifie la méthode HTTP
  })
  if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
  }
  let musicBlob = await response.blob()
  let musiqueUrlAudio = URL.createObjectURL(musicBlob);
  return musiqueUrlAudio
}

async function loadTrack(track_index) {
    // Clear the previous seek timer
    clearInterval(updateTimer);
    resetValues();

    // Load a new track
    curr_track.src = await getMusicFile(track_list[track_index].id)
    curr_track.load();
    console.log("track list dans loadTrack : ", track_list)
    // Update details of the track
    // track_art.style.backgroundImage = 
    //    "url(" + track_list[track_index].image + ")";
    track_name.textContent = track_list[track_index].name;
    track_artist.textContent = track_list[track_index].auteur;
    now_playing.textContent = 
       "PLAYING " + (track_index + 1) + " OF " + track_list.length;
   
    // Set an interval of 1000 milliseconds
    // for updating the seek slider
    updateTimer = setInterval(seekUpdate, 1000);
   
    // Move to the next track if the current finishes playing
    // using the 'ended' event
    curr_track.addEventListener("ended", nextTrack);
   
    // // Apply a random background color
    random_bg_color();
  }

  function random_bg_color() {
    // Get a random number between 64 to 256
    // (for getting lighter colors)
    let red = Math.floor(Math.random() * 256) + 64;
    let green = Math.floor(Math.random() * 256) + 64;
    let blue = Math.floor(Math.random() * 256) + 64;
   
    // Construct a color with the given values
    let bgColor = "rgb(" + red + ", " + green + ", " + blue + ")";
   
    // Set the background to the new color
    document.body.style.background = bgColor;
  }

// Function to reset all values to their default
function resetValues() {
    curr_time.textContent = "00:00";
    total_duration.textContent = "00:00";
    seek_slider.value = 0;
  }

  function playpauseTrack() {
    // Switch between playing and pausing
    // depending on the current state
    if (!isPlaying) playTrack();
    else pauseTrack();
    }
    
    function playTrack() {
    // Play the loaded track
    curr_track.play();
    isPlaying = true;
    
    // Replace icon with the pause icon
    playpause_btn.innerHTML = '<i class="play-button">&#x23F8;</i>';
    }
    
    function pauseTrack() {
    // Pause the loaded track
    curr_track.pause();
    isPlaying = false;
    
    // Replace icon with the play icon
    playpause_btn.innerHTML = '<i class="play-button">&#9658;</i>';
    }
    
    async function nextTrack() {
    // Go back to the first track if the
    // current one is the last in the track list
    if (track_index < track_list.length - 1)
        track_index += 1;
    else track_index = 0;
    
    // Load and play the new track
    await loadTrack(track_index);
    playTrack();
    }
    
    async function prevTrack() {
    // Go back to the last track if the
    // current one is the first in the track list
    if (track_index > 0)
        track_index -= 1;
    else track_index = track_list.length - 1;
    
    // Load and play the new track
    await loadTrack(track_index);
    playTrack();
    }
    
    function seekTo() {
        // Calculate the seek position by the
        // percentage of the seek slider 
        // and get the relative duration to the track
        seekto = curr_track.duration * (seek_slider.value / 100);
       
        // Set the current track position to the calculated seek position
        curr_track.currentTime = seekto;
      }
       
      function setVolume() {
        // Set the volume according to the
        // percentage of the volume slider set
        curr_track.volume = volume_slider.value / 100;
      }
       
      function seekUpdate() {
        let seekPosition = 0;
       
        // Check if the current track duration is a legible number
        if (!isNaN(curr_track.duration)) {
          seekPosition = curr_track.currentTime * (100 / curr_track.duration);
          seek_slider.value = seekPosition;
       
          // Calculate the time left and the total duration
          let currentMinutes = Math.floor(curr_track.currentTime / 60);
          let currentSeconds = Math.floor(curr_track.currentTime - currentMinutes * 60);
          let durationMinutes = Math.floor(curr_track.duration / 60);
          let durationSeconds = Math.floor(curr_track.duration - durationMinutes * 60);
       
          // Add a zero to the single digit time values
          if (currentSeconds < 10) { currentSeconds = "0" + currentSeconds; }
          if (durationSeconds < 10) { durationSeconds = "0" + durationSeconds; }
          if (currentMinutes < 10) { currentMinutes = "0" + currentMinutes; }
          if (durationMinutes < 10) { durationMinutes = "0" + durationMinutes; }
       
          // Display the updated duration
          curr_time.textContent = currentMinutes + ":" + currentSeconds;
          total_duration.textContent = durationMinutes + ":" + durationSeconds;
        }
      }

// We get all the tracks available on the website
// TO DO : Improve this (with different type of tracks, agile tracks Vs. transitory traks )
let track_list = fetch(urlMusicListenable,{
  method: "GET",
  mode: "cors",
  credentials: "same-origin",

  }
).then(response => {
  if (!response.ok) {
    throw new Error(`HTTP error! Status: ${response.status}`);
  }
  return response.json(); // Convertir la réponse en JSON
})
.then(data => {
  // Assigner les données reçues à track_list
  track_list = data;
  // Load the first track in the tracklist
  console.log("Tracks received:", track_list[0].name);
  loadTrack(track_index);
})
.then(() => {
  console.log("Track chargée")
})
.catch(error => {
  console.error("Error fetching tracks:", error);
});
