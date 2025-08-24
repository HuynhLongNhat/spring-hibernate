package form;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.Constants;
import utils.Helper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Struts Form bean for T003 screen.
 * <p>
 * Holds customer data (for add/edit operations) and performs 
 * server-side validation on input fields.
 * </p>
 * 
 * @author 
 * @version 1.0
 * @since 2025-08-25
 */
public class T003Form extends ActionForm {

	/** Serial version UID for serialization. */
	private static final long serialVersionUID = 1L;

	/** Unique customer ID. */
	private int customerId;

	/** Customer's full name. */
	private String customerName;

	/** Customer's gender ("0" for male, "1" for female). */
	private String sex;

	/** Customer's birthday (format: yyyy/MM/dd). */
	private String birthday;

	/** Customer's email address. */
	private String email;

	/** Customer's home address. */
	private String address;

	/** Form mode: "ADD" (new customer) or "EDIT" (update customer). */
	private String mode;

	// =========================
	// Getters & Setters
	// =========================

	/**
	 * Gets customer ID.
	 * @return customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * Sets customer ID.
	 * @param customerId unique identifier
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets customer name.
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Sets customer name.
	 * @param customerName full name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Gets gender.
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Sets gender.
	 * @param sex "0" or "1"
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * Gets birthday.
	 * @return birthday string
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Sets birthday.
	 * @param birthday formatted as yyyy/MM/dd
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets email.
	 * @return email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 * @param email valid email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets address.
	 * @return address string
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets address.
	 * @param address home address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets form mode.
	 * @return mode ("ADD" or "EDIT")
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Sets form mode.
	 * @param mode "ADD" or "EDIT"
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	// =========================
	// Validation
	// =========================

	/**
	 * Validates form input when action is SAVE.
	 * <p>
	 * Rules:
	 * <ul>
	 *   <li>Birthday must not be empty and must follow yyyy/MM/dd format.</li>
	 *   <li>Email must not be empty and must match standard regex format.</li>
	 * </ul>
	 * </p>
	 *
	 * @param mapping action mapping
	 * @param request current HTTP request
	 * @return errors if validation fails, empty if valid
	 */
	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		String action = request.getParameter(Constants.PARAM_ACTION);

		if (Constants.ACTION_SAVE.equals(action)) {
			// Birthday validation
			if (Helper.isEmpty(birthday)) {
				errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_BIRTHDAY_INVALID));
				return errors;
			} else {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate.parse(birthday.trim(), formatter);
				} catch (DateTimeParseException e) {
					errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_BIRTHDAY_INVALID));
					return errors;
				}
			}

			// Email validation
			if (Helper.isEmpty(email)) {
				errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_EMAIL_INVALID));
				return errors;
			} else {
				String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
				if (!email.matches(emailRegex)) {
					errors.add(Constants.GLOBAL, new ActionMessage(Constants.ERROR_MSG_EMAIL_INVALID));
					return errors;
				}
			}
		}
		return errors;
	}

}
