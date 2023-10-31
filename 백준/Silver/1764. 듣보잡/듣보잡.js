const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const nInput = n.split(" ").map((x) => Number(x));
const noListenCount = nInput[0];

//Set 자료구조 익히기
//dic형태로 할 필요 없음!!
let noListenSet = new Set();
let noSeeSet = new Set();

//N+2부터가아니라 N+2줄부터였음;;
for (let i = 0; i < input.length; i++) {
  if (i < noListenCount) {
    noListenSet.add(input[i]);
  } else {
    noSeeSet.add(input[i]);
  }
}

let answer = [];
noListenSet.forEach((element) => {
  if (noSeeSet.has(element)) answer.push(element);
});
answer.sort();
console.log(answer.length + "\n" + answer.join("\n"));
