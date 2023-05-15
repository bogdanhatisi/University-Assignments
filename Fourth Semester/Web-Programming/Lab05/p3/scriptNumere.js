const valueArray = [
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
let clickedValues = [];
let tileIDs = [];
let flippedTiles = 0;

Array.prototype.shuffle = function () {
  for (let i = this.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [this[i], this[j]] = [this[j], this[i]];
  }
};

function start() {
  flippedTiles = 0;
  let output = "";
  valueArray.shuffle();
  for (let i = 0; i < valueArray.length; i++) {
    output += `<div id="tile_${i}" onclick="flip(this, '${valueArray[i]}')"></div>`;
  }
  $("#board").html(output);
}

function flip(tile, val) {
  if ($(tile).html() === "" && clickedValues.length < 2) {
    $(tile).css("background", "#FFF");
    $(tile).css("color", "green");
    $(tile).html(val);
    if (clickedValues.length === 0) {
      clickedValues.push(val);
      tileIDs.push(tile.id);
    } else if (clickedValues.length === 1) {
      clickedValues.push(val);
      tileIDs.push(tile.id);
      if (clickedValues[0] === clickedValues[1]) {
        flippedTiles += 2;
        $(`#${tileIDs[0]}`).css("background-color", "#ADD8E6");
        $(`#${tileIDs[1]}`).css("background-color", "#ADD8E6");
        clickedValues = [];
        tileIDs = [];
        if (flippedTiles === valueArray.length) {
          alert("Felicitari, ai terminat jocul!");
          $("#board").html("");
          startNewGame();
        }
      } else {
        function flipBack() {
          $(`#${tileIDs[0]}`).html("");
          $(`#${tileIDs[1]}`).html("");
          clickedValues = [];
          tileIDs = [];
        }
        setTimeout(flipBack, 700);
      }
    }
  }
}
