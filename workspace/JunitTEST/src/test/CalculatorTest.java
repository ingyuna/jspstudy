package test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Calculator;

class CalculatorTest {

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Before");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("after");
	}

	@Test	// 이 메소드는 테스트 메소드이다.
	@DisplayName("1 + 1 = 2")
	void test() {
		Calculator calculator = new Calculator();
		// assertNull(calculator); 		// null이면 통과, 아니면 실패      // -> calculator가 null인지 물어봄.
		// assertNotNull(calculator);		// not null이면 통과, 아니면 실패
		assertEquals(3, calculator.add(1, 1), "1 + 1 = 2 이어야 한다."); 	// expected(기대한값), actual(실제로 발생한 값)   // 값이 같은지 비교 
		System.out.println("test");
		// fail("Not yet implemented");		// 실패했을 때 메시지 만들어주는것.
		
		
		/*
		if (DAO.getInstance().insert(person) == 0) {
			fail("삽입 실패");
		}
		*/
		
		/*
		assertEquals(1, DAO.getInstance().insert(person), "삽입 실패");	 // => insert의 결과가 1이 되기를 기대한다.
		*/
		
	}

}
