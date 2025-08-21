package action;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import dto.T001Dto;
import dto.T002Dto;
import form.T003Form;
import service.T003Service;
import utils.Helper;

/**
 * Action class for managing customer details (screen T003).
 * <p>
 * Handles loading customer data for editing or creating new customers,
 * and saving customer information (insert/update).
 * </p>
 */
public class T003Action extends MappingDispatchAction {

    private T003Service t003Service;

    /**
     * Setter for dependency injection.
     *
     * @param t003Service the service layer used for customer operations
     */
    public void setT003Service(T003Service t003Service) {
        this.t003Service = t003Service;
    }

    /**
     * Loads the customer information into the form for display in screen T003.
     * If {@code customerId} is provided, fetches customer data for editing.
     * Otherwise, sets the form mode to ADD for new customer creation.
     *
     * @param mapping  the action mapping
     * @param form     the Struts form bean
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return an {@link ActionForward} to the T003 screen
     * @throws Exception if an error occurs during processing
     */
    public ActionForward load(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!Helper.isLogin(request)) {
            return new ActionForward("T001.do", true);
        }

        T003Form t003Form = (T003Form) form;
        String customerIdStr = request.getParameter("customerId");

        if (customerIdStr != null && !customerIdStr.trim().isEmpty()) {
            try {
                Integer customerId = Integer.parseInt(customerIdStr.trim());
                T002Dto customer = t003Service.getCustomerById(customerId);
                if (customer != null) {
                    t003Form.setCustomerId(customer.getCustomerID());
                    t003Form.setCustomerName(customer.getCustomerName());
                    t003Form.setSex(customer.getSex());
                    t003Form.setBirthday(customer.getBirthday());
                    t003Form.setEmail(customer.getEmail());
                    t003Form.setAddress(customer.getAddress());
                    t003Form.setMode("EDIT");
                } else {
                    t003Form.setMode("ADD");
                }
            } catch (NumberFormatException e) {
                t003Form.setMode("ADD");
            }
        } else {
            t003Form.setMode("ADD");
        }

        return mapping.findForward("T003");
    }

    /**
     * Saves customer information (insert or update) depending on the form mode.
     * <ul>
     *   <li>ADD → creates a new customer</li>
     *   <li>EDIT → updates existing customer</li>
     * </ul>
     *
     * @param mapping  the action mapping
     * @param form     the Struts form bean
     * @param request  the HTTP servlet request
     * @param response the HTTP servlet response
     * @return an {@link ActionForward} to T002 screen after save, 
     *         or back to T003 if an error occurs
     * @throws Exception if an application-level error occurs
     */
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!Helper.isLogin(request)) {
            return new ActionForward("T001.do", true);
        }

        T003Form t003Form = (T003Form) form;

        T002Dto customer = new T002Dto();
        customer.setCustomerID(t003Form.getCustomerId());
        customer.setCustomerName(t003Form.getCustomerName());
        customer.setSex(t003Form.getSex());
        customer.setBirthday(t003Form.getBirthday());
        customer.setEmail(t003Form.getEmail());
        customer.setAddress(t003Form.getAddress());

        String mode = t003Form.getMode();

        HttpSession session = request.getSession(false);
        T001Dto loggedInUser = (T001Dto) session.getAttribute("user");
        int psnCd = (loggedInUser != null) ? loggedInUser.getPsnCd() : null;

        try {
            if ("ADD".equals(mode)) {
                t003Service.insertCustomer(customer, psnCd);
            } else {
                t003Service.updateCustomer(customer, psnCd);
            }
            return new ActionForward("T002.do", true);
        } catch (SQLException e) {
            throw new ServletException("Database error occurred while saving customer.", e);
        } catch (Exception e) {
            return mapping.findForward("T003");
        }
    }
}
