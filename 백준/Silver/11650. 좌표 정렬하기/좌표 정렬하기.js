const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const coordinate = input.map((x) => x.split(" ").map((x) => Number(x)));

coordinate.sort((a, b) => {
  //리턴값이 1이면 그대로, -1이면 바꿈, 0이면 순서를 보장하지 않음
  if (a[0] > b[0]) return 1;
  else if (a[0] < b[0]) return -1;
  else if (a[1] > b[1]) return 1;
  else return -1;
});

console.log(coordinate.join("\n").replace(/,/g, " "));
