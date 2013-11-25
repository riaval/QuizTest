package ua.riaval.quiztest.controller.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import ua.riaval.quiztest.dao.QuizResultDAO;
import ua.riaval.quiztest.dao.implementation.OrderBy;
import ua.riaval.quiztest.entity.QuizResult;

public class ResultsDataModel extends LazyDataModel<QuizResult> {

	private static final long serialVersionUID = 1L;

	private QuizResultDAO quizResultDAO;
	private List<QuizResult> datasource;

	public ResultsDataModel(QuizResultDAO quizResultDAO) {
		this.quizResultDAO = quizResultDAO;
	}

	@Override
	public QuizResult getRowData(String rowKey) {
		int id = Integer.parseInt(rowKey);
		
		return quizResultDAO.findByID(id);
	}

	@Override
	public Object getRowKey(QuizResult quizResult) {
		return quizResult.getId();
	}

	@Override
	public List<QuizResult> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, String> filters) {
		datasource = quizResultDAO.loadPart(first, pageSize, OrderBy.DESC);
		this.setRowCount(quizResultDAO.count());
		return datasource;
	}

}
