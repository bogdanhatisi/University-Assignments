let valueArray = [
  "0",
  "0",
  "1",
  "1",
  "2",
  "2",
  "3",
  "3",
  "4",
  "4",
  "5",
  "5",
  "6",
  "6",
  "7",
  "7",
  "8",
  "8",
  "9",
  "9",
];
let values = [];
let tileIds = [];
let flippedTiles = 0;

Array.prototype.tile_shuffle = function () {
  for (let i = this.length - 1; i > 0; i--) {
    let j = Math.floor(Math.random() * (i + 1));
    let temp = this[j];
    this[j] = this[i];
    this[i] = temp;
  }
};

function start() {
  flippedTiles = 0;
  let output = "";
  valueArray.tile_shuffle();
  for (let i = 0; i < valueArray.length; i++) {
    output +=
      '<div id = "tile_' +
      i +
      '" onclick = "flip(this,\'' +
      valueArray[i] +
      "')\"></div>";
  }
  document.getElementById("board").innerHTML = output;
}

function flip(tile, val) {
  if (tile.innerHTML === "" && values.length < 2) {
    tile.style.background = "url(img/" + val + ".jpg) no-repeat";
    tile.style.color = "green";
    tile.innerHTML = val;
    if (values.length === 0) {
      values.push(val);
      tileIds.push(tile.id);
    } else if (values.length === 1) {
      values.push(val);
      tileIds.push(tile.id);
      if (values[0] === values[1]) {
        flippedTiles += 2;
        values = [];
        tileIds = [];
        if (flippedTiles === valueArray.length) {
          alert("Felicitari, ai terminat jocul!");
          document.getElementById("board").innerHTML = "";
          startNewGame();
        }
      } else {
        function flipBack() {
          let tile1 = document.getElementById(tileIds[0]);
          let tile2 = document.getElementById(tileIds[1]);
          tile1.innerHTML = "";
          tile1.style.background = "#FFFFFF";
          tile2.innerHTML = "";
          tile2.style.background = "#FFFFFF";
          values = [];
          tileIds = [];
        }
        setTimeout(flipBack, 700);
      }
    }
  }
}
