package ua.riaval.quiztest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.context.RequestContext;

import ua.riaval.quiztest.dao.CategoryDAO;
import ua.riaval.quiztest.dao.QuestionTypeDAO;
import ua.riaval.quiztest.dao.QuizDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.Answer;
import ua.riaval.quiztest.entity.Category;
import ua.riaval.quiztest.entity.Question;
import ua.riaval.quiztest.entity.QuestionType;
import ua.riaval.quiztest.entity.Quiz;

@ManagedBean
@ViewScoped
public class NewQuizBean {

	@PostConstruct
	private void postConstract() {
		categories = categoryDAO.findAll(OrderBy.DESC);
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String[] getCheckedItems() {
		return checkedItems;
	}

	public void setCheckedItems(String[] checkedItems) {
		this.checkedItems = checkedItems;
	}

	public String getCheckedItem() {
		return checkedItem;
	}

	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public int[] getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(int[] selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	public void addAnswer() {
		Answer answer = new Answer();
		answer.setQuestion(question);
		answers.add(answer);
	}

	public void removeAnswer() {
		int size = answers.size();
		if (size > 0) {
			answers.remove(size - 1);
		}
	}

	public void addQuestion() {
		switch (questionType) {
		case "single":
			prepareForSingleQuestion();
			break;
		case "multiple":
			prepereForMultipleQuestion();
			break;
		case "open":
			prepereForOpenQuestion();
			break;
		default:
			break;
		}
		
		question.getAnswers().addAll(answers);
		QuestionType objectType = questionTypeDAO.findByTypeName(questionType);
		question.setQuestionType(objectType);
		
		if (question.getId() == null) {
			quiz.getQuestions().add(question);
			question.setQuiz(quiz);
		}

		clear();

		RequestContext.getCurrentInstance().execute("addQuestionDialog.hide()");
		RequestContext.getCurrentInstance().update("form:dialog");
	}
	
	private void prepareForSingleQuestion() {
		for (Answer answer : answers) {
			answer.setCorrect(false);
		}
		try {
			int selectedIndex = Integer.parseInt(checkedItem);
			answers.get(selectedIndex).setCorrect(true);
		} catch (NumberFormatException e) {
			return;
		}
	}

	private void prepereForMultipleQuestion() {
		for (Answer answer : answers) {
			answer.setCorrect(false);
		}
		for (String checkedItem : checkedItems) {
			try {
				int selectedIndex = Integer.parseInt(checkedItem);
				answers.get(selectedIndex).setCorrect(true);
			} catch (NumberFormatException e) {
				continue;
			}
		}
	}

	private void prepereForOpenQuestion() {
		for (Answer answer : answers) {
			answer.setCorrect(true);
		}
	}

	public void editQuestion(Question question) {
		this.question = question;
		answers.clear();
		this.answers.addAll(question.getAnswers());
		
		List<String> checkedAnswers = new ArrayList<>();
		for (int i = 0; i < answers.size(); i++) {
			if (answers.get(i).getCorrect()) {
				checkedAnswers.add(String.valueOf(i));
			}
		}
		
		
		if (question.getQuestionType().getName().equals("multiple")) {
			checkedItems = Arrays.copyOf(checkedAnswers.toArray(), checkedAnswers.size(), String[].class);
		} else if (question.getQuestionType().getName().equals("single")) {
			checkedItem = checkedAnswers.get(0);
		}
		
		questionType = question.getQuestionType().getName();
		
		RequestContext.getCurrentInstance().update("form:dialog");
		RequestContext.getCurrentInstance().execute("addQuestionDialog.show()");
	}
	
	private void clear() {
		question = new Question();
		answers.clear();

		questionType = null;
		checkedItems = null;
		checkedItem = null;
	}

	public String create() {
		for (int index : selectedCategories) {
			Category category = categories.get(index);
			quiz.getCategories().add(category);
		}
		quizDAO.save(quiz);
		
		return "quizzes?faces-redirect=true";
	}

	@EJB
	private CategoryDAO categoryDAO;
	@EJB
	private QuizDAO quizDAO;
	@EJB
	private QuestionTypeDAO questionTypeDAO;

	private List<Category> categories;
	private Quiz quiz = new Quiz();
	private Question question = new Question();
	private List<Answer> answers = new ArrayList<Answer>();

	private String questionType;
	private String[] checkedItems;
	private String checkedItem;
	private int[] selectedCategories;

}
