const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const num = input.map((x) => Number(x));

if (n === "0") {
  console.log(0);
} else {
  num.sort((a, b) => a - b);

  let cut = Math.round(num.length * 0.15);

  const newArr = num.slice(cut, num.length - cut);

  let sum = 0;
  newArr.forEach((element) => {
    sum += element;
  });

  console.log(Math.round(sum / newArr.length));
}
