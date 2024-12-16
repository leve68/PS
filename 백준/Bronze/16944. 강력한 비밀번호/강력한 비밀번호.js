const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let input = [];
rl.on("line", (line) => {
  input.push(line.split(""));
  if (input.length === 2) rl.close();
});

//추가해야하는 문자 개수
let answerTable = Array(4).fill(false);
const special = "!@#$%^&*()-+";
const specialTable = special.split("");

//6자 이상
//0: 숫자 포함
//1: 소문자 포함
//2: 대문자 포함
//3: 특수문자 포함 !@#$%^&*()-+

rl.on("close", () => {
  let answer = 6 - Number(input[0]) > 0 ? 6 - Number(input[0]) : 0;
  const s = input[1];

  s.forEach((element) => {
    if (!isNaN(element)) {
      answerTable[0] = true;
    } else if (
      "a".charCodeAt() <= element.charCodeAt() &&
      element.charCodeAt() <= "z".charCodeAt()
    ) {
      answerTable[1] = true;
    } else if (
      "A".charCodeAt() <= element.charCodeAt() &&
      element.charCodeAt() <= "Z".charCodeAt()
    ) {
      answerTable[2] = true;
    } else if (specialTable.includes(element)) {
      answerTable[3] = true;
    }
  });

  let count = 0;
  answerTable.forEach((element) => {
    if (!element) count++;
  });

  if (answer < count) answer = count;

  console.log(answer);
  process.exit();
});
