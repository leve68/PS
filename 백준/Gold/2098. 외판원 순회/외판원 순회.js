const [num, ...w] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n")
  .map((x) => x.split(" ").map((v) => Number(v)));

const vertexCount = num[0];

//TSP 외판원 문제
//dp[i][route] = (현재 위치가 i이고 지금까지 route를 거쳐서 왔을 때 드는 비용의 최소값)
//dp[next][currnet] = TSP(current, visitedSet) + w[current][next]

//1을 n만큼 왼쪽쉬프트하면 2의 n승이 된다
//2의 n승 = n의 부분집합의 개수

//저장할 dp
const setCount = 1 << vertexCount;
let cost = Array.from({ length: vertexCount }, () => Array(setCount).fill(-1));

function Tsp(now, visitedBit) {
  //전부 방문했다면
  if (visitedBit === setCount - 1) {
    if (w[now][0] == 0) return Infinity;
    else return w[now][0];
  }

  if (cost[now][visitedBit] !== -1) return cost[now][visitedBit];

  //시작부터 Inf로 초기화하게 되면 무한히 반복할 가능성이 있음
  //Infinity에는 어떠한 연산을 가해도 Infinity라서 방문을해도 길이없으면 안한것과같음
  cost[now][visitedBit] = Infinity;

  for (let i = 0; i < vertexCount; i++) {
    //길이 없으면 conitnue
    if (w[now][i] === 0) continue;
    //비트마스킹 i번째 비트가 1이면(이미 방문했으면) continue
    if (visitedBit & (1 << i)) continue;

    cost[now][visitedBit] = Math.min(
      cost[now][visitedBit],
      w[now][i] + Tsp(i, visitedBit | (1 << i))
    );
  }

  return cost[now][visitedBit];
}

console.log(Tsp(0, 1));
