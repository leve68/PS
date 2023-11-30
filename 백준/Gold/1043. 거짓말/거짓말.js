const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const peopleCount = input[0][0];
const truthCount = input[1][0];

let myQueue = new Queue();
let truthMap = new Map();
for (let i = 1; i <= truthCount; i++) {
  truthMap.set(input[1][i], true);
  myQueue.myPush(input[1][i]);
}

//각 파티 참가후 영향을 주는 edge맵 생성
let partyComingMap = new Map();
for (let i = 2; i < input.length; i++) {
  const partyComingCount = input[i][0];
  let tempArr = [];
  for (let j = 1; j <= partyComingCount; j++) {
    tempArr.push(input[i][j]);
  }
  for (let j = 1; j <= partyComingCount; j++) {
    if (partyComingMap.get(input[i][j])) {
      let temp = partyComingMap.get(input[i][j]);
      for (let k = 0; k < tempArr.length; k++) {
        if (!temp.includes(tempArr[k])) temp.push(tempArr[k]);
      }
    } else partyComingMap.set(input[i][j], tempArr);
  }
}

//사실을 알 수 있게 되는 사람들 모두 체크
let visited = Array(peopleCount).fill(false);
while (myQueue.size > 0) {
  let root = myQueue.myPop();
  visited[root] = true;
  truthMap.set(root, true);
  let nextPeople = partyComingMap.get(root);
  if (nextPeople) {
    for (let i = 0; i < nextPeople.length; i++) {
      if (!visited[nextPeople[i]]) myQueue.myPush(nextPeople[i]);
    }
  }
}

let count = 0;
for (let i = 2; i < input.length; i++) {
  const partyComingCount = input[i][0];
  for (let j = 1; j <= partyComingCount; j++) {
    if (!truthMap.get(input[i][j])) {
      count++;
      break;
    }
  }
}
console.log(count);

//BFS사용을 위한 큐
function Node(value) {
  this.value = value;
  this.next = null;
}

function Queue() {
  this.front = null;
  this.back = null;
  this.size = 0;

  this.myPush = function (value) {
    const node = new Node(value);
    if (this.size === 0) {
      this.front = node;
      this.back = node;
    } else {
      this.back.next = node;
      this.back = node;
    }
    this.size++;
  };

  this.myPop = function () {
    let node;
    if (this.size === 0) {
      console.log("error");
    } else {
      node = this.front;
      this.front = this.front.next;
      this.size--;
      return node.value;
    }
  };
}
