const fs = require("fs");
const input = fs.readFileSync("dev/stdin").toString().trim();

let N = 0;
let i;

for (i = 666; ; i++) {
  if (i.toString().includes("666")) {
    N++;
  }
  if (N == input) break;
}

console.log(i);
