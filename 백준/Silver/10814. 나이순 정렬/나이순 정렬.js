const [n, ...input] = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

const members = input.map((x) => x.split(" "));

members.sort((a, b) => {
  if (a[0] === b[0]) {
    return 0;
  }
  return Number(a[0]) < Number(b[0]) ? -1 : 1;
});

console.log(members.join("\n").replace(/,/g, " ")); // /,/g 는 전체문자열에서 ,를 선택함
