function solution(dice) {
  var answer = [];

  const n = dice.length;
  const diceArr = dice.map((_, index) => index);

  //n개 중에서 2/n 고르기
  const diceSelectArr = getSelectedArr(diceArr, n / 2);
  let diceSelectValueArr = [];
  diceSelectArr.forEach((select) => {
    let diceValue = [];
    select.forEach((diceIndex) => {
      diceValue.push(dice[diceIndex]);
    });
    diceSelectValueArr.push(getDiceSelectValueArr(diceValue));
  });

  let winCountArr = [];
  for (let i = 0; i < diceSelectArr.length; i++) {
    let a = i;
    let b = diceSelectArr.length - 1 - i;
    const n = diceSelectValueArr[a].length;

    let winACount = 0;
    let winBCount = 0;
    let x = 0;
    let y = 0;
      
    while (true) {
      if (x === n || y === n) {
        if (y === n) winACount += n * (n - x);
        winCountArr[a] = winACount;
        break;
      }
      if (diceSelectValueArr[a][x] > diceSelectValueArr[b][y]) {
         y++;
         winACount ++;
          
      } else {
        winACount += y;
        x++;
      }
    }
  }

    console.log(winCountArr);
    let max = 0;
    let maxIndex = 0;
    winCountArr.forEach((count, index) => {
        if(count>max){
            max = count;
            maxIndex = index;
        }
    });
    answer = diceSelectArr[maxIndex].map((v)=>v+1);
  return answer;
}

function getDiceSelectValueArr(arr) {
  let selected = [];
  let selectedArr = [];

  function select(start) {
    if (selected.length === arr.length) {
      let sum = 0;
      selected.forEach((v) => {
        sum += v;
      });
      selectedArr.push(sum);
      return;
    }

    for (let i = 0; i < arr[start].length; i++) {
      selected.push(arr[start][i]);
      select(start + 1);
      selected.pop();
    }
  }
  select(0);
  return selectedArr.sort((a, b) => a - b);
}

function getSelectedArr(arr, m) {
  let selected = [];
  let selectedArr = [];

  function select(start) {
    if (selected.length === m) {
      selectedArr.push([...selected]);
      return;
    }

    for (let i = start; i < arr.length; i++) {
      selected.push(i);
      select(i + 1);
      selected.pop();
    }
  }

  select(0);
  return selectedArr;
}
