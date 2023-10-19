const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const coordinate = input.map((x) => x.split(" ").map((x) => Number(x)));

coordinate.sort((a, b) => {
  if (a[1] > b[1]) return 1;
  else if (a[1] < b[1]) return -1;
  else if (a[0] > b[0]) return 1;
  else return -1;
});

console.log(coordinate.join("\n").replace(/,/g, " "));
