const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const testCount = input[0][0];
//1개의 testCase = [건물개수, 순서개수],[건물들 소요 시간],[순서]*순서개수,[건설해야 할 건물] => 3+순서개수

let testCase = [];
let count = 0;
for (let i = 1; i < input.length; i++) {
  testCase[count] = [input[i]];
  for (let j = 1; j < testCase[count][0][1] + 3; j++) {
    testCase[count].push(input[i + j]);
  }
  i = i + testCase[count][0][1] + 2;
  count++;
}

//각 vertex = 건물번호
//각 edge 가중치 = 걸리는 시간 => ex) 1=>2 2번 10초 : [1,2,10]
//목표 건물까지 걸리는 가장 긴 시간 = 최소시간

//순서가 정해진 작업 => 위상정렬 알고리즘 => 큐 자료구조 이용
//1=>2, 3=>2 이면 2 의 건설시간은 Math.max(1,3)+2

let answer = [];
for (let i = 0; i < testCount; i++) {
  GetWeight(testCase[i]);
}
console.log(answer.join("\n"));

function GetWeight(testCase) {
  //위상정렬 위한 큐
  let myQueue = new Queue();
  //테스트케이스 값 받아오기
  const vertexCount = testCase[0][0];
  const edgeCount = testCase[0][1];
  const weight = [0, ...testCase[1]];
  const endVertex = testCase[edgeCount + 2][0];
  //endEdgeMap[도착점] = 도착점을 가리키는 edge시작점 배열
  let endEdgeMap = Array.from({ length: vertexCount + 1 }, () => []);
  for (let i = 2; i < edgeCount + 2; i++) {
    endEdgeMap[testCase[i][1]].push(testCase[i][0]);
  }
  //startEdgeMap[출발점] = 출발점에서 출발한 edge도착점 배열
  let startEdgeMap = Array.from({ length: vertexCount + 1 }, () => []);
  for (let i = 2; i < edgeCount + 2; i++) {
    startEdgeMap[testCase[i][0]].push(testCase[i][1]);
  }
  //들어오는 edge수 관리
  let inDegree = [0];
  let startVertex;
  for (let i = 1; i < vertexCount + 1; i++) {
    inDegree[i] = endEdgeMap[i].length;
    if (inDegree[i] === 0) startVertex = i;
  }
  //방문 여부 저장
  let visited = Array(vertexCount + 1).fill(false);
  visited[0] = true;
  visited[startVertex] = true;

  let sortedArr = [];

  //위상정렬
  myQueue.myPush(startVertex);
  while (myQueue.size > 0) {
    let currentV = myQueue.myPop();
    sortedArr.push(currentV);
    let nextVArr = startEdgeMap[currentV];
    for (let i = 0; i < nextVArr.length; i++) {
      inDegree[nextVArr[i]]--;
    }
    //들어오는 edge가 0이면 큐에 삽입
    for (let i = 1; i < inDegree.length; i++) {
      if (inDegree[i] === 0 && !visited[i]) {
        myQueue.myPush(i);
        visited[i] = true;
      }
    }
  }

  //dp[도착점] = 도착점까지 걸린 최소 시간
  //dp[도착점] = Math.max(dp[출발점1],dp[출발점2])+도착점 건설시간
  let dp = Array(vertexCount + 1).fill(0);
  dp[startVertex] = weight[startVertex];

  for (let i = 1; i < sortedArr.length; i++) {
    let currentV = sortedArr[i];
    let max = 0;
    for (let j = 0; j < endEdgeMap[currentV].length; j++) {
      if (dp[endEdgeMap[currentV][j]] > max) max = dp[endEdgeMap[currentV][j]];
    }
    dp[currentV] = max + weight[currentV];
  }

  answer.push(dp[endVertex]);
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
