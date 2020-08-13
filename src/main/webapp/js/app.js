const URL = window.location.href;
const SONG_URL = `${URL}api/song`;

const form = document.getElementById("form");

form.addEventListener("submit",event => {
    event.preventDefault();

    requestSong()
        .then(checkStatus)
        .then(response => response.json())
        .then(addCardItem)
        .catch(handleError);
})

function requestSong() {
    let artistInput = document.getElementById("artist");
    const artist = artistInput.value;
    artistInput.value = "";

    let songInput = document.getElementById("song");
    const song = songInput.value;
    songInput.value = "";



    const fetchURL = `${SONG_URL}?artist=${artist}&song=${song}`;

    console.log(fetchURL);

    return fetch(fetchURL);
}

function addCardItem(json) {
    const artist = json.artist;
    const title = json.title;
    const albumName = json.albumName;
    const songArtUrl = json.songArtUrl;

    let card = document.createElement("div");
    
    card.innerHTML = `
        <div class="col mb-4">
            <div class="card text-white bg-dark mb-3">
                <img src="${songArtUrl}" class="card-img-top" alt="${title}">
                <div class="card-header">${artist}</div>
                <div class="card-body">
                    <h5 class="card-title">${title}</h5>
                    <p class="card-text">Album: ${albumName}</p>
                </div>
            </div>
        </div>
    `;

    console.log(card.innerHTML);

    document.getElementById("playlist").appendChild(card);
}

function checkStatus(response) {
    if(response.ok) {
        return Promise.resolve(response);
    } else {
        return Promise.reject(new Error(response.statusText));
    }
}

function handleError(error) {
    console.log(error);
    alert("Song not found.");
}