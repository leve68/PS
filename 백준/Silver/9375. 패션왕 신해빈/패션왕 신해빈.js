const [n, ...inputString] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

const testCount = Number(n);
let test = Array(testCount);

let count = -1;
for (let i = 0; i < inputString.length; i++) {
  if (!isNaN(inputString[i])) {
    count++;
    test[count] = [Number(inputString[i])];
  } else test[count].push(inputString[i].split(" "));
}

let answer = [];
for (let i = 0; i < testCount; i++) {
  let clothCount = test[i][0];
  let map = new Map();
  let keys = [];
  for (let j = 1; j <= clothCount; j++) {
    if (!map.get(test[i][j][1])) {
      //선택하지 않는 경우 포함
      map.set(test[i][j][1], 2);
      keys.push(test[i][j][1]);
    } else {
      let temp = map.get(test[i][j][1]) + 1;
      map.set(test[i][j][1], temp);
    }
  }
  answer.push(Count(map, keys));
}
console.log(answer.join("\n"));

function Count(map) {
  let sum = 1;
  map.forEach((element) => {
    sum *= element;
  });
  return sum - 1;
}
