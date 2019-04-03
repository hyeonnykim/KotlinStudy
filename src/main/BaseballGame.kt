package main

val ROUND_COUNT: Int = 9

fun main(args: Array<String>) {
	process()
}

fun process() {
	// create random number
	val randNum: String = createRandomValue()

	var stringInput: String
	var round: Int = 1
	while (true) {
		print("Enter text: ")
		stringInput = readLine()!!

		if (stringInput.length == 3) {
			if (checkSameNum(stringInput)) {
				println("Don't duplicate same number")
				continue
			}

			println("===== Round ${round} =====")
			println("You entered: $stringInput")
			val result: IntArray = compare(randNum, stringInput)
			if (result[0] == 3) {
				println("${result[0]} Strike!!! Game End")
				break
			} else {
				println("${result[0]} Strike / ${result[1]} Ball / ${result[2]} Out")
			}
		} else {
			println("You need enter to 3-digits")
			continue
		}

		round++

		if (round > ROUND_COUNT) {
			println("Round End. Game Over!!! Original Number is ${randNum}")
			break
		}
	}
}

fun checkSameNum(stringInput: String): Boolean {
	for ((k, v: Int) in stringInput.groupingBy { it }.eachCount().filter { it.value > 1 }) {
		if (v > 1) {
			return true
		}
	}
	return false
}


fun compare(originVal: String, inpVal: String): IntArray {
	var result: IntArray = intArrayOf(0, 0, 0)

	for ((i: Int, c: Char) in inpVal.withIndex()) {
		if (originVal.indexOf(c) == -1) {
			result[2] += 1  // Out
		} else if (originVal.indexOf(c) == i) {
			result[0] += 1    // Strike
		} else {
			result[1] += 1    // Ball
		}
	}

	return result
}

// 서로 다른숫자로 랜덤뽑기
fun createRandomValue(len: Int = 3): String {    // default value
	var result: String = ""    // 수정 가능한 변수 
	val numDic: Array<Int> = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) // 불변 변수

	while (result.length < len) {
		val randomNumber: Int = (0..9).random()
		if (result.contains(randomNumber.toString())) {
			continue;
		}
		result += numDic.get(randomNumber)
	}

	return result
}