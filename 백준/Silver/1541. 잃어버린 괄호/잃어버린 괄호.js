const input = require("fs").readFileSync("/dev/stdin").toString();

let parsed = [];
parsed = GetParsed(parsed, input);

//"-" 등장하면 괄호 열기, 끝나거나 "-" 등장하면 괄호 닫기
let newArr = [];
let count = 0;
for (let i = 0; i < parsed.length; i++) {
  if (parsed[i] === "-" && count === 0) {
    newArr.push(parsed[i]);
    newArr.push("(");
    count++;
  } else if (parsed[i] === "-" && count > 0) {
    newArr.push(")");
    newArr.push(parsed[i]);
    newArr.push("(");
  } else newArr.push(parsed[i]);
}
while (count !== 0) {
  newArr.push(")");
  count--;
}

console.log(GetAnswer(newArr));

//파싱된 배열을 리턴
function GetParsed(parsed, input) {
  let temp = "";
  for (let i = 0; i < input.length; i++) {
    if (input[i] === "-" || input[i] === "+") {
      parsed.push(Number(temp));
      parsed.push(input[i]);
      temp = "";
    } else {
      temp += input[i];
    }
  }
  parsed.push(Number(temp));
  return parsed;
}

function GetAnswer(parsed) {
  let temp = "";
  for (let i = 0; i < parsed.length; i++) {
    temp += parsed[i];
  }
  return eval(temp);
}
