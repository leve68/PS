let [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const info = n.split(" ").map((x) => Number(x));
const edge = input.map((x) => x.split(" ").map((x) => Number(x)));

//유저번호 : vertex
//친구 관계 : edge, 가중치 1
const vertexCount = info[0];
const edgeCount = info[1];
//케빈 베이컨 = 모든 사람들과 연결된 최소 가중치합 => 전부 알아야하므로 너비우선(BFS)

//visited 선언
let visited = [];
SetFalse(visited);

//graph 선언 + index = vertex가 되도록 하고 관련된 edge를 모두 정렬해서 저장
let graph = [];
for (let i = 1; i <= vertexCount; i++) {
  graph = PushEdgeInGraph(edge, graph, i);
}

//weight의 index는 목표 출발 vertex, value는 [index별 각 목표까지의 가중치]
//weight[1][1] = 0; start는 1, dest는 1, 가중치는 0
let weight = [];
let graphQueue = new Queue();
for (let startVertex = 1; startVertex <= vertexCount; startVertex++) {
  //visited 와 weight[startVertex] 초기화
  SetFalse(visited);
  weight[startVertex] = SetWeight(weight, startVertex, graph);

  //queue에 넣고 BFS실행
  graphQueue.myPush(startVertex);
  while (graphQueue.size !== 0) {
    let root = graphQueue.myPop();
    visited[root] = true;

    for (let i = 0; i < graph[root].length; i++) {
      let vertex1 = graph[root][i][1];
      let vertex2 = graph[root][i][0];
      if (!visited[vertex1]) {
        //방문하지 않은 간선 queue에 추가
        graphQueue.myPush(vertex1);
        visited[vertex1] = true;

        //이전 weight에 1을 더한값을 넣음
        weight[startVertex][vertex1] = weight[startVertex][root] + 1;
      } else if (!visited[vertex2]) {
        graphQueue.myPush(vertex2);
        visited[vertex2] = true;
        weight[startVertex][vertex2] = weight[startVertex][root] + 1;
      }
    }
  }
}

let kevin = [Number.MAX_SAFE_INTEGER];
for (let i = 1; i < weight.length; i++) {
  let sum = 0;
  for (let j = 1; j < weight[i].length; j++) {
    sum += weight[i][j];
  }
  kevin[i] = sum;
}

let min = Math.min(...kevin);
for (let i = 1; i < kevin.length; i++) {
  if (kevin[i] === min) {
    min = i;
    break;
  }
}
console.log(min);

//visited를 false로 초기화
function SetFalse(visited) {
  for (let i = 1; i <= vertexCount; i++) {
    visited[i] = false;
  }
}

//weight[startVertex]를 모두 0으로 초기화
function SetWeight(weight, startVertex, graph) {
  let temp = Array();
  for (let i = 1; i <= vertexCount; i++) {
    temp[i] = 0;
  }
  weight[startVertex] = temp;
  return weight[startVertex];
}

//그래프에 vertex와 관련된 edge는 모두 graph의 vertex인덱스에 넣기
function PushEdgeInGraph(edge, graph, vertex) {
  let tempArr = new Array();
  for (let j = 0; j < edge.length; j++) {
    let temp = [edge[j][0], edge[j][1]];
    if (edge[j][0] === vertex) {
      tempArr.push(temp);
    } else if (edge[j][1] === vertex) {
      tempArr.push(temp.reverse());
    }
  }
  graph[vertex] = tempArr.sort((a, b) => a[1] - b[1]);
  return graph;
}

//BFS구현을 위한 Queue구현
function Node(value) {
  this.value = value;
  this.next = null;
}

function Queue() {
  this.size = 0;
  this.front = null;
  this.back = null;

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
    if (this.size === 0) {
      console.log("error");
    } else {
      const node = this.front;
      this.front = node.next;
      this.size--;
      return node.value;
    }
  };
}
