const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split(" ")
  .map((x) => Number(x));

let set1 = [1, 2, 3, 4, 5, 6, 7, 8];
let set2 = [8, 7, 6, 5, 4, 3, 2, 1];

let up = true;
let down = true;
for (let i = 0; i < 8; i++) {
  if (input[i] !== set1[i]) up = false;
  if (input[i] !== set2[i]) down = false;
}

if (up) console.log("ascending");
else if (down) console.log("descending");
else console.log("mixed");
