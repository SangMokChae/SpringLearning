package spring.di.ui;

import spring.di.entity.Exam;

public class InlineExamConsole implements ExamConsole {
	
	private Exam exam;
	
	public InlineExamConsole(Exam exam) {
		this.exam = exam;
	}

	@Override
	public void print() {
		System.out.printf("total is %d, avg is %f\n", exam.total(), exam.avg());
		// printf = format하겠다. // inlineExam console은 출력시에 한줄로 출력하겠다고 정의
		// %d = exam.total(), %f = exam.avg()가 된다.
	}
}
