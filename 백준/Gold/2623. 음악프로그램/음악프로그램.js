const [num, ...input] = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

const n = num[0];
const m = num[1];
let edgeSet = Array.from({ length: n + 1 }, () => new Set());

//순서가 정해진 정렬 => 위상정렬
for (let i = 0; i < m; i++) {
  for (let j = 1; j < input[i].length - 1; j++) {
    for (let k = j + 1; k < input[i].length; k++) {
      edgeSet[input[i][j]].add(input[i][k]);
    }
  }
}

let inDegree = Array(n + 1).fill(0);
for (let i = 1; i < n + 1; i++) {
  edgeSet[i].forEach((element) => {
    inDegree[element]++;
  });
}

let myQueue = new Queue();
for (let i = 1; i < inDegree.length; i++) {
  if (inDegree[i] === 0) myQueue.myPush(i);
}

let answer = [];
while (myQueue.size > 0) {
  let current = myQueue.myPop();
  answer.push(current);

  edgeSet[current].forEach((element) => {
    inDegree[element]--;
    if (inDegree[element] === 0) {
      myQueue.myPush(element);
    }
  });
}

if (answer.length === n) console.log(answer.join("\n"));
else console.log(0);

//큐 구현
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
