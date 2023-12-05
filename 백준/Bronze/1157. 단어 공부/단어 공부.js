const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("")
  .map((x) => x.toLowerCase());

let alphaMap = new Map();
for (let i = 0; i < input.length; i++) {
  if (!alphaMap.get(input[i])) {
    alphaMap.set(input[i], 1);
  } else {
    alphaMap.set(input[i], alphaMap.get(input[i]) + 1);
  }
}

let arr = [...alphaMap.entries()];
let max = arr.reduce((a, b) => (a[1] > b[1] ? a : b));
let count = 0;
alphaMap.forEach((element) => {
  if (element === max[1]) count++;
});
if (count === 1) console.log(max[0].toUpperCase());
else console.log("?");
