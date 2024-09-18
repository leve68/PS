const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];

rl.on("line", (line) => {
  input.push(line);
  if (input.length === Number(input[0].split(" ")[0]) + 1) {
    rl.close();
  }
});

rl.on("close", () => {
  const condition = input[0].split(" ").map((v) => Number(v));
  const n = condition[0];
  const m = condition[1];

  input.shift(1);

  //가장 먼 거리 찾기
  //트리의 지름 구하기 => 시작점에서 가장 먼 거리 찾기 => 그 위치에서 가장 먼 거리 찾기
  //BFS 사용하는게 좋을듯
  //왜 visited가 수정되지 않고 다시 0이 추가되어서 필요없는 루프를 도는가....? => getStartNode 에서 전체 초기화하기 때문
  //1칸만 이동하면 시작점이 0이라서 다시 시작점으로 돌아와 2가 출려됨 => 시작점을 1로 두고 결과값에서 1을 빼는 방식으로 해결
  //L이 하나 뿐일때 distance가 0이라서 -1이 출력되는 문제 => 초기 distance를 1로 설정

  // 4 4
  // WWWL
  // LLWL
  // LLWW
  // LWLL
  //이 경우 처음 설정한 노드에서 가장 먼 노드가 2개 발생 => 하나만 정답임

  //다 지우고 다시 설계
  //육지가 트리의 형태가 아닐 수 있음 => 순환 가능성
  //그냥 완전탐색

  const map = input.map((v) => v.split(""));
  let visited;
  visitedInit();

  let max = 0;
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (visited[i][j] === 0) {
        let distance = getDistance([i, j]);
        if (distance > max) max = distance;
        visitedInit();
      }
    }
  }

  console.log(max);

  process.exit();

  function visitedInit() {
    visited = map.map((v) =>
      v.map((x) => {
        if (x === "W") return -1;
        else return 0;
      })
    );
  }

  function getDistance(start) {
    const dx = [1, 0, -1, 0];
    const dy = [0, -1, 0, 1];

    let queue = new Queue();
    let distance = 0;
    queue.myPush(start);
    visited[start[0]][start[1]] = 1;
    while (queue.size > 0) {
      let current = queue.myPop();
      let curY = current[0];
      let curX = current[1];

      for (let i = 0; i < 4; i++) {
        let nextY = curY + dy[i];
        let nextX = curX + dx[i];
        if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m) {
          if (
            visited[nextY][nextX] === 0 ||
            visited[nextY][nextX] > visited[curY][curX] + 1
          ) {
            visited[nextY][nextX] = visited[curY][curX] + 1;
            if (visited[nextY][nextX] > distance)
              distance = visited[nextY][nextX];
            queue.myPush([nextY, nextX]);
          }
        }
      }
    }
    return distance - 1;
  }
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
