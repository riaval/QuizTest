package ua.riaval.quiztest.controller;

import java.util.ArrayList;
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
public class CreateQuizBean {

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
		// System.out.println("-------------addAnswer------------");
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
		// System.out.println("-------------addQuestion------------");
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
		question.setQuiz(quiz);
		quiz.getQuestions().add(question);

		clear();

		RequestContext.getCurrentInstance().execute("addQuestionDialog.hide()");
		RequestContext.getCurrentInstance().update("form:dialog");
	}

	private void prepareForSingleQuestion() {
		try {
			int selectedIndex = Integer.parseInt(checkedItem);
			answers.get(selectedIndex).setCorrect(true);
		} catch (NumberFormatException e) {
			return;
		}
	}

	private void prepereForMultipleQuestion() {
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

	private void clear() {
		question = new Question();
		answers.clear();

		questionType = null;
		checkedItems = null;
		checkedItem = null;
	}

	public void create() {
		for (int index : selectedCategories) {
			// Category category = categoryDAO.findByID(1);
			Category category = categories.get(index);
			quiz.getCategories().add(category);
		}
		quizDAO.save(quiz);
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
