const [num, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim("")
  .split("\n");

const number = Number(num);
let length = input[0].length;

let map = input.map((v) => (0 + v + 0).split(""));
map.unshift(Array(length + 2).fill("0"));
map.push(Array(length + 2).fill("0"));
let newMap = input.map((v) => (0 + v + 0).split(""));
newMap.unshift(Array(length + 2).fill("0"));
newMap.push(Array(length + 2).fill("0"));
//정상 : 0과 다른 색을 침범하지 못하는 DFS
//색약 : 같은 색 범위 하나 밖의 색들을 0으로 만듦

let count = 1;
let newCount = 1;
for (let i = 1; i < number + 2; i++) {
  for (let j = 1; j < length + 2; j++) {
    if (isNaN(map[i][j])) {
      map = ChangeMap(i, j, map, count); //map 업데이트
      count++;
    }
    if (isNaN(newMap[i][j])) {
      newMap = ChangeNewMap(i, j, newMap, newCount);
      newCount++;
    }
  }
}
console.log(Math.max(...newMap.flat()), Math.max(...map.flat()));
//DFS
function ChangeMap(y, x, map, count) {
  const dx = [1, 0, -1, 0];
  const dy = [0, -1, 0, 1];
  let color = map[y][x];
  map[y][x] = count;
  for (let i = 0; i < 4; i++) {
    if (map[y + dy[i]][x + dx[i]] === color && color === "B") {
      map = ChangeMap(y + dy[i], x + dx[i], map, count);
    } else if (
      (color === "R" || color === "G") &&
      (map[y + dy[i]][x + dx[i]] === "R" || map[y + dy[i]][x + dx[i]] === "G")
    ) {
      map = ChangeMap(y + dy[i], x + dx[i], map, count);
    }
  }
  return map;
}
function ChangeNewMap(y, x, map, count) {
  const dx = [1, 0, -1, 0];
  const dy = [0, -1, 0, 1];
  let color = map[y][x];
  map[y][x] = count;
  for (let i = 0; i < 4; i++) {
    if (map[y + dy[i]][x + dx[i]] === color) {
      map = ChangeNewMap(y + dy[i], x + dx[i], map, count);
    }
  }
  return map;
}

// //BFS
// function ChangeNewMap(y, x, map, count) {
//   const dx = [1, 0, -1, 0];
//   const dy = [0, -1, 0, 1];
//   let myQueue = new Queue();
//   let color = map[y][x];
//   myQueue.myPush([y, x]);
//   while (myQueue.size !== 0) {
//     let current = myQueue.myPop();
//     let curX = current[1];
//     let curY = current[0];
//     map[curY][curX] = count;

//     for (let i = 0; i < 4; i++) {
//       if (map[curY + dy[i]][curX + dx[i]] === color) {
//         myQueue.myPush([curY + dy[i], curX + dx[i]]);
//       }
//     }
//   }
//   return map;
// }

// function ChangeMap(y, x, map, count) {
//   const dx = [1, 0, -1, 0];
//   const dy = [0, -1, 0, 1];
//   let myQueue = new Queue();
//   let color = map[y][x];
//   myQueue.myPush([y, x]);
//   while (myQueue.size !== 0) {
//     let current = myQueue.myPop();
//     let curX = current[1];
//     let curY = current[0];
//     map[curY][curX] = count;

//     for (let i = 0; i < 4; i++) {
//       if (color === "B" && map[curY + dy[i]][curX + dx[i]] === color) {
//         myQueue.myPush([curY + dy[i], curX + dx[i]]);
//       } else if (
//         ((color === "R" || color === "G") &&
//           map[curY + dy[i]][curX + dx[i]] === "R") ||
//         map[curY + dy[i]][curX + dx[i]] === "G"
//       ) {
//         myQueue.myPush([curY + dy[i], curX + dx[i]]);
//       }
//     }
//   }
//   return map;
// }

// //BFS사용을 위한 Queue
// function Node(value) {
//   this.value = value;
//   this.next = null;
// }

// function Queue() {
//   this.size = 0;
//   this.front = null;
//   this.back = null;

//   this.myPush = function (value) {
//     const node = new Node(value);
//     if (this.size === 0) {
//       this.front = node;
//       this.back = node;
//     } else {
//       this.back.next = node;
//       this.back = node;
//     }
//     this.size++;
//   };

//   this.myPop = function () {
//     if (this.size === 0) {
//       console.log("error");
//     } else {
//       const node = this.front;
//       this.front = node.next;
//       this.size--;
//       return node.value;
//     }
//   };
// }
