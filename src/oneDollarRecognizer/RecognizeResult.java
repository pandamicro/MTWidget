package oneDollarRecognizer;

public class RecognizeResult {
	private Template template;
	private double score;
	//private double angle;
	
	public RecognizeResult(Template template, double score) {
		this.template = template;
		this.score = score;
		//this.angle = angle;
	}
	
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
