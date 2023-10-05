const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [n, ...numbers] = input.map((x) => Number(x));

let testFloor = [];
let testRoom = [];

for (let i = 0; i < numbers.length; i++) {
  if (i % 2 === 0) {
    testFloor.push(numbers[i]);
  } else {
    testRoom.push(numbers[i]);
  }
}

const maxFloor = Math.max(...testFloor);
const maxRoom = Math.max(...testRoom);

let floor = [];
let room = [];
let people = 0;

for (let i = 0; i <= maxRoom; i++) {
  room[i] = i;
}
floor[0] = room;

for (let i = 1; i <= maxFloor; i++) {
  let tempArr = new Array();
  floor[i] = tempArr;
  for (let j = 1; j <= maxRoom; j++) {
    for (let k = 1; k <= j; k++) {
      people += floor[i - 1][k];
    }
    floor[i][j] = people;
    people = 0;
  }
}

let answer = [];
for (let i = 0; i < Number(n); i++) {
  answer[i] = floor[testFloor[i]][testRoom[i]];
}

console.log(answer.join("\n"));
