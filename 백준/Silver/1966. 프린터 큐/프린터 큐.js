const input = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");
const [n, ...cases] = input.map((v) => v.split(" ").map((x) => Number(x)));

for (let i = 0; i < n; i++) {
  let count = 1;
  let location = cases[i * 2][1];
  let queue = cases[i * 2 + 1];
  while (true) {
    const Item = queue.shift();
    if (Math.max(...queue) <= Item && location === 0) {
      console.log(count);
      break;
    } else if (Math.max(...queue) > Item && location === 0) {
      queue.push(Item);
      location = queue.length - 1;
    } else if (Math.max(...queue) > Item) {
      queue.push(Item);
      location -= 1;
    } else if (Math.max(...queue) <= Item) {
      //젤앞이 우선순위 젤 크면 바로 출력
      count += 1;
      location -= 1;
    }
  }
}