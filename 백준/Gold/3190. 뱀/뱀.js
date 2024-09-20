const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line.split(" "));
  if (input.length <= 3) return;
  if (input.length <= Number(input[1][0]) + 2) return;
  if (
    input.length ===
    Number(input[1][0]) + Number(input[2 + Number(input[1][0])][0]) + 3
  ) {
    rl.close();
  }
});

rl.on("close", () => {
  const n = Number(input[0][0]);
  const appleCount = Number(input[1][0]);
  const appleList = input
    .slice(2, 2 + appleCount)
    .map((v) => v.map((x) => Number(x)));
  const changeCount = Number(input[2 + appleCount]);
  const changeList = input.slice(3 + appleCount, 3 + appleCount + changeCount);

  const map = Array.from({ length: n }, () => Array(n).fill(0)); //0은 빈공간
  appleList.forEach(([i, j]) => (map[j - 1][i - 1] = 1)); //1은 사과

  // 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
  //머리가 움직인 경로를 기억해야 하므로 큐를 사용해야 할듯

  let second = 0;
  let next = [
    [0, 1],
    [1, 0],
    [0, -1],
    [-1, 0],
  ]; //이동 방향
  let way = 0;
  let changeIndex = 0;

  let queue = new Queue(); //뱀이 차지하는 공간 => back: 머리 front: 꼬리
  queue.myPush([0, 0]);
  map[0][0] = -1; //-1은 뱀이 차지하는 공간
  while (true) {
    second++;
    const head = queue.back.value;
    const nextX = head[1] + next[way][0];
    const nextY = head[0] + next[way][1];
    if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < n) {
      if (map[nextY][nextX] === 0) {
        // 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다.
        const prev = queue.myPop();
        map[prev[0]][prev[1]] = 0;
        queue.myPush([nextY, nextX]);
        map[nextY][nextX] = -1;
      } else if (map[nextY][nextX] === 1) {
        // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
        queue.myPush([nextY, nextX]);
        map[nextY][nextX] = -1;
      } else break; // 자기자신의 몸과 부딪히면 게임이 끝난다.
    } else break; // 벽과 부딪히면 게임이 끝난다.

    // 게임 시작 시간으로부터 X초가 끝난 뒤에 방향 회전
    if (second === Number(changeList[changeIndex][0])) {
      if (changeList[changeIndex][1] === "D") {
        way = (way + 1) % 4;
      } else {
        if (way > 0) way = (way - 1) % 4;
        else way = 3;
      }
      if (changeIndex + 1 < changeCount) changeIndex++;
    }
  }

  console.log(second);
  process.exit();
});

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
