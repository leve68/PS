const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n");

const caseCount = input[0];

let idx = 1;
let answerArr = [];
for (let i = 0; i < caseCount; i++) {
  idx = testCase(idx);
}
console.log(answerArr.join("\n"));

function testCase(idx) {
  const caseInfo = input[idx].split(" ").map((x) => Number(x));
  const h = caseInfo[0];
  const w = caseInfo[1];

  let caseMap = [];
  caseMap[0] = Array(w + 2).fill(".");
  for (let j = 1; j < h + 1; j++) {
    idx++;
    caseMap[j] = [".", ...input[idx].split(""), "."];
  }
  caseMap[h + 1] = Array(w + 2).fill(".");

  idx++;
  const myKeys = input[idx] === "0" ? [] : input[idx].split("");
  answerArr.push(getAnswer(h, w, caseMap, myKeys));

  idx++;
  return idx;
}

function getAnswer(h, w, caseMap, myKeys) {
  const dh = [0, -1, 0, 1];
  const dw = [1, 0, -1, 0];

  let doors = Array.from({ length: 28 }, () => []);
  let keys = Array(28).fill(false);
  let answer = 0;

  //keys 에 가진 키 입력
  for (let i = 0; i < myKeys.length; i++) {
    keys[myKeys[i].charCodeAt() - 97] = true;
  }

  //$: 목표
  //.: 빈 공간
  //*: 벽
  //바깥에서 출발

  //출발점에서 bfs
  let myQueue = new Queue();
  myQueue.myPush([0, 0]);
  caseMap[0][0] = "*";

  //접근 가능한 문들은 door 에 위치 저장
  //keys에 이미 있는 문들은 바로 개방
  while (myQueue.size > 0) {
    let current = myQueue.myPop();
    let curH = current[0];
    let curW = current[1];

    for (let i = 0; i < 4; i++) {
      let nextH = curH + dh[i];
      let nextW = curW + dw[i];
      if (nextH < h + 2 && nextH >= 0 && nextW < w + 2 && nextW >= 0) {
        let value = caseMap[nextH][nextW].charCodeAt();
        if (value === 46) {
          //.
          caseMap[nextH][nextW] = "*";
          myQueue.myPush([nextH, nextW]);
        } else if (value === 36) {
          //$
          answer++;
          caseMap[nextH][nextW] = "*";
          myQueue.myPush([nextH, nextW]);
        } else if (65 <= value && value <= 90) {
          //대문자
          //door
          if (keys[value - 65]) {
            caseMap[nextH][nextW] = "*";
            myQueue.myPush([nextH, nextW]);
          } else {
            //doors에 위치 저장
            doors[value - 65].push([nextH, nextW]);
          }
        } else if (97 <= value && value <= 122) {
          //소문자
          //key
          caseMap[nextH][nextW] = "*";
          keys[value - 97] = true;
          //얻은 키로 열 수 있는 문이 있다면
          if (doors[value - 97].length > 0) {
            for (let element of doors[value - 97]) {
              caseMap[element[0]][element[1]] = "*";
              myQueue.myPush([element[0], element[1]]);
            }
            doors[value - 97] = [];
          }
          myQueue.myPush([nextH, nextW]);
        }
      }
    }
  }
  return answer;
}

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
