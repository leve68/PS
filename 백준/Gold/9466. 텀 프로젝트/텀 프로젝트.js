const input = require("fs")
  .readFileSync(0, "utf-8")
  .toString()
  .trim()
  .split("\n")
  .map((v) => v.split(" ").map((x) => Number(x)));

//순환하는 경우 찾기
//단방향 그래프 생성

const caseCount = input[0][0];
let idx = 1;
let answerArr = [];
for (let i = 0; i < caseCount; i++) {
  idx = testCase(idx);
}
console.log(answerArr.join("\n"));

function testCase(index) {
  const vertexCount = input[index][0];
  index++;
  const edgeMap = [0, ...input[index]];
  index++;

  answerArr.push(getAnswer(vertexCount, edgeMap));

  return index;
}

function getAnswer(vertexCount, edgeMap) {
  let sumCount = 0; //팀에 속하지 못한 학생 수
  let visited = Array(vertexCount + 1).fill(false); //검사한 vertex 저장
  let order = new Map(); //방문 순서 저장

  for (let i = 1; i < vertexCount + 1; i++) {
    order.clear();
    let start = i;
    let current = start;
    let next;

    if (!visited[start]) {
      let count = 0;
      visited[start] = true;
      order.set(start, 1);

      while (true) {
        next = edgeMap[current];
        if (!order.has(next) && !visited[next]) {
          visited[next] = true;
          order.set(next, order.get(current) + 1);
          current = next;
        } else if (order.has(next) && order.get(next) !== 0) {
          count = order.get(current) - order.get(next) + 1;
          break;
        } else {
          break;
        }
      }
      sumCount += count;
    }
  }

  return vertexCount - sumCount;
}
