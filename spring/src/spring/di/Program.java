package spring.di;

import spring.di.entity.Exam;
import spring.di.entity.NewlecExam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamConsole;

public class Program {
	public static void main(String[] args) {
		Exam exam = new NewlecExam();
//		ExamConsole console = new InlineExamConsole(exam); // 한줄 출력 -> DI
		ExamConsole console = new GridExamConsole(exam); // 그리드 모양으로 출력
		// 인라인 방식에서 그리드방식으로 바꿔야할때 소스코드 수정없이 할려면 코드로 존재하는게 아니라 외부 설정으로 바꿔야 한다.
		// ExamConsole console = []; // Spring을 이용하여 뺏다 꽂았다 결합을 달리 가능하다. 
		console.print();
	}
}
