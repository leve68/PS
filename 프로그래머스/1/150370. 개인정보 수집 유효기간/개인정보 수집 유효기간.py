def solution(today, terms, privacies):
    answer = []
    # 오늘 파기해야할 개인정보 구하기
    todayNum = int(today.replace(".",""))
    termsMap = {element.split(" ")[0] : int(element.split(" ")[1]) for element in terms}
    privaciesArr = [
        [item.split(" ")[0].split(".")] + [termsMap.get(item.split(" ")[1])]
        for item in privacies
    ]
    
    index = 1
    for i in privaciesArr:
        dateNum = getExpireDate(i[0],i[1])
        if dateNum<=todayNum:
            answer.append(index)
        index += 1

    
    return answer

def getExpireDate(dateArr, month):
    expireDate = 0
    newMonth = int(dateArr[1]) + month
    
    if(newMonth>12):
        dateArr[0] = str(int(dateArr[0]) + (newMonth // 12))
        dateArr[1] = str(newMonth % 12)
        if(dateArr[1] == "0"):
            dateArr[1] = "12"
            dateArr[0] = str(int(dateArr[0]) - 1)
    else:
        dateArr[1] = str(newMonth)
    
    if len(dateArr[1]) == 1:
        dateArr[1] = "0" + dateArr[1]
    
    expireDate = int("".join(dateArr))
    return expireDate