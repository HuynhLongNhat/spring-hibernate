package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.MappingDispatchAction;

import dto.T001Dto;
import form.T001Form;
import service.T001Service;
import utils.Helper;

/**
 * Action class responsible for handling user login functionality.
 * 
 * <p>This class extends {@link MappingDispatchAction} to provide 
 * multiple action methods within a single class. It delegates 
 * authentication logic to {@link T001Service}.</p>
 * 
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Display the login form</li>
 *   <li>Authenticate user credentials</li>
 *   <li>Maintain user session on successful login</li>
 * </ul>
 * 
 * @author YourName
 */
public class T001Action extends MappingDispatchAction {

    /**
     * Service used for user authentication logic.
     */
    private T001Service t001Service;

    /**
     * Setter for dependency injection of {@link T001Service}.
     *
     * @param t001Service the service used for user authentication
     */
    public void setT001Service(T001Service t001Service) {
        this.t001Service = t001Service;
    }

    /**
     * Displays the login form. If the user is already logged in,
     * they will be redirected to the customer list page.
     *
     * @param mapping   the ActionMapping used to select this instance
     * @param form      the optional ActionForm bean for this request
     * @param request   the HTTP request
     * @param response  the HTTP response
     * @return          an ActionForward to either login page or T002.do
     * @throws Exception if an error occurs during processing
     */
    public ActionForward showLoginForm(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) 
                                       throws Exception {
        if (Helper.isLogin(request)) {
            return new ActionForward("T002.do", true);
        } else {
            return mapping.findForward("T001");
        }
    }

    /**
     * Handles user login submission. Validates credentials via service,
     * creates a session on success, or returns an error message on failure.
     *
     * @param mapping   the ActionMapping used to select this instance
     * @param form      the ActionForm bean containing login data
     * @param request   the HTTP request
     * @param response  the HTTP response
     * @return          an ActionForward to the next page or back to login
     * @throws Exception if an error occurs during processing
     */
    @SuppressWarnings("deprecation")
	public ActionForward getUserLogin(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) 
                                      throws Exception {
        T001Form loginForm = (T001Form) form;

        // Map form to DTO
        T001Dto inputDto = new T001Dto();
        inputDto.setUserId(loginForm.getUserId());
        inputDto.setPassword(loginForm.getPassword());

        // Call service for authentication
        T001Dto user = t001Service.getUserLogin(inputDto);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return new ActionForward("T002.do", true);
        } else {
            ActionErrors errors = new ActionErrors();
            errors.add("errorMessage", new ActionMessage("error.login.failed"));
            saveErrors(request, errors);
            return mapping.findForward("T001");
        }
    }
}
