const fs = require("fs");
const input = fs.readFileSync("dev/stdin").toString().trim().split("\n");

const count = input.length - 1;
let inputEnd;
let result = [];
let isSame;

for (let i = 0; i < count; i++) {
  inputEnd = input[i].length;
  input[i].toString();
  isSame = "true";
  for (let j = 0; j < inputEnd - j - 1; j++) {
    if (input[i][j] !== input[i][inputEnd - j - 1]) {
      isSame = "false";
    }
  }
  if (isSame === "true") {
    result[i] = "yes";
  } else {
    result[i] = "no";
  }
}

for (let k = 0; k < count; k++) {
  console.log(result[k]);
}
