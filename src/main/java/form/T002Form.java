package form;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.Constants;
import utils.Helper;

/**
 * Form bean for the T002 screen (Customer Search).
 * <p>
 * Captures user input for search criteria, selected customers, 
 * and pagination state.
 * </p>
 * 
 * @author YourName
 * @version 1.1
 * @since 2025-07-21
 */
public class T002Form extends ActionForm {

	/** Serial version UID for serialization. */
	private static final long serialVersionUID = 1L;

	// =========================
	// Search criteria fields
	// =========================
	private String customerName;
	private String sex;
	private String birthdayFrom;
	private String birthdayTo;

	// =========================
	// Selected customers (bulk actions)
	// =========================
	private int[] customerIds;

	// =========================
	// Pagination fields
	// =========================
	private int currentPage = 1;
	private int prevPage = 1;
	private int nextPage = 1;
	private int totalPages = 1;

	// =========================
	// Getters and Setters
	// =========================

	/**
	 * Gets the customer name filter.
	 * @return customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets the customer name filter.
	 * @param customerName name to filter
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets the gender filter.
	 * @return gender value
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets the gender filter.
	 * @param sex gender value
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets the start birthday filter.
	 * @return birthday from
	 */
	public String getBirthdayFrom() {
		return birthdayFrom;
	}

	/**
	 * Sets the start birthday filter.
	 * @param birthdayFrom start date
	 */
	public void setBirthdayFrom(String birthdayFrom) {
		this.birthdayFrom = birthdayFrom;
	}

	/**
	 * Gets the end birthday filter.
	 * @return birthday to
	 */
	public String getBirthdayTo() {
		return birthdayTo;
	}

	/**
	 * Sets the end birthday filter.
	 * @param birthdayTo end date
	 */
	public void setBirthdayTo(String birthdayTo) {
		this.birthdayTo = birthdayTo;
	}

	/**
	 * Gets selected customer IDs.
	 * @return array of IDs
	 */
	public int[] getCustomerIds() {
		return customerIds;
	}

	/**
	 * Sets selected customer IDs.
	 * @param customerIds array of IDs
	 */
	public void setCustomerIds(int[] customerIds) {
		this.customerIds = customerIds;
	}

	/**
	 * Gets current page number.
	 * @return current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets current page number.
	 * @param currentPage page value
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Gets previous page number.
	 * @return prev page
	 */
	public int getPrevPage() {
		return prevPage;
	}

	/**
	 * Sets previous page number.
	 * @param prevPage page value
	 */
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}

	/**
	 * Gets next page number.
	 * @return next page
	 */
	public int getNextPage() {
		return nextPage;
	}

	/**
	 * Sets next page number.
	 * @param nextPage page value
	 */
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * Gets total pages.
	 * @return total pages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets total pages.
	 * @param totalPages number of pages
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	// =========================
	// Validation logic
	// =========================

	/**
	 * Validates form input depending on action type.
	 * @param mapping action mapping
	 * @param request HTTP request
	 * @return errors if validation fails
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		String action = request.getParameter(Constants.PARAM_ACTION);
		if (Constants.ACTION_REMOVE.equals(action)) {
			if (customerIds == null || customerIds.length == 0) {
				errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_CUSTOMER_ID_REQUIRED));
				return errors;
			}
		} else if (Constants.ACTION_SEARCH.equals(action)) {
			validateBirthday(errors);
		}
		return errors;
	}

	/**
	 * Validates birthday range and format.
	 * @param errors error collection
	 */
	private void validateBirthday(ActionErrors errors) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		try {
			LocalDate from = null;
			LocalDate to = null;

			if (!Helper.isEmpty(birthdayFrom)) {
				from = LocalDate.parse(birthdayFrom.trim(), formatter);
			}
			if (!Helper.isEmpty(birthdayTo)) {
				to = LocalDate.parse(birthdayTo.trim(), formatter);
			}
			if (from != null && to != null && to.isBefore(from)) {
				errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_BIRTHDAY_RANGE));
			}
		} catch (DateTimeParseException e) {
			if (e.getParsedString().equals(birthdayFrom)) {
				errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_BIRTHDAY_FROM_FORMAT));
			} else {
				errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_BIRTHDAY_TO_FORMAT));
			}
		}
	}

}
