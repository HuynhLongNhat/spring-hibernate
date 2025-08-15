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

public class T001Action extends MappingDispatchAction {

    // Spring sẽ inject qua setter
    private T001Service t001Service;

    public void setT001Service(T001Service t001Service) {
        this.t001Service = t001Service;
    }

    public ActionForward showLoginForm(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (Helper.isLogin(request)) {
            return new ActionForward("T002.do", true);
        } else {
            return mapping.findForward("T001");
        }
    }

    @SuppressWarnings("deprecation")
	public ActionForward getUserLogin(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        T001Form loginForm = (T001Form) form;

        // Map form sang DTO
        T001Dto t001Dto = new T001Dto();
        t001Dto.setUserId(loginForm.getUserId());
        t001Dto.setPassword(loginForm.getPassword());

        // Gọi service trực tiếp (đã được Spring inject)
        T001Dto user = t001Service.getUserLogin(t001Dto);

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
