const URL = window.location.href;
const SONG_URL = `${URL}api/song`

const form = document.getElementById("form");

form.addEventListener("submit",event => {
    event.preventDefault();

    requestSong()
        .then(response => response.json())
        .then(addCardItem);
})

function requestSong() {
    const artist = document.getElementById("artist").value;
    const song = document.getElementById("song").value;

    const fetchURL = `${SONG_URL}?artist=${artist}&song=${song}`;

    console.log(fetchURL);

    return fetch(fetchURL);
}

function addCardItem(json) {
    const artist = json.artist;
    const title = json.title;
    const albumName = json.albumName;
    const songArtUrl = json.songArtUrl;

    let li = document.createElement("li");

    li.innerHTML=`<img src="${songArtUrl}">`;

    document.getElementById("playlist").appendChild(li);
}
