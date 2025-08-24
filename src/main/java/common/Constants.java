package common;

/**
 * Centralized class for storing application-wide constants.
 *
 * <p>This class defines all static constant values used throughout the 
 * application such as screen forwards, action parameters, modes, 
 * session keys, request parameters, and error message keys.</p>
 *
 * <p>Using constants improves maintainability, avoids hardcoding 
 * strings across different layers (Controller, Service, DAO, JSP), 
 * and ensures consistency.</p>
 *
 * @author YourName
 * @version 1.0
 * @since 2025-08-21
 */
public class Constants {

    // Global scope identifier
    public static final String GLOBAL = "global";

    /* ============================
       Action forwards (navigation)
       ============================ */
    // Forward to login screen (T001)
    public static final String T001_LOGIN = "T001";
    // Forward to customer search screen (T002)
    public static final String T002_SEARCH = "T002";
    // Forward to customer edit screen (T003)
    public static final String T003_EDIT = "T003";

    /* ============================
       Action parameters (request)
       ============================ */
    // Login action
    public static final String ACTION_LOGIN = "login";
    // Remove action
    public static final String ACTION_REMOVE = "remove";
    // Search action
    public static final String ACTION_SEARCH = "search";
    // Logout action
    public static final String ACTION_LOGOUT = "logout";
    // Save action (insert/update)
    public static final String ACTION_SAVE = "save";

    /* ============================
       Mode identifiers
       ============================ */
    // Edit mode
    public static final String MODE_EDIT = "EDIT";
    // Add mode
    public static final String MODE_ADD = "ADD";

    /* ============================
       Pagination
       ============================ */
    // Records per page
    public static final int PAGE_SIZE = 15;

    /* ============================
       Session attributes
       ============================ */
    // Logged-in user
    public static final String SESSION_USER = "user";
    // Search condition (T002SCO)
    public static final String SESSION_T002_SCO = "T002SCO";

    /* ============================
       Request / Form parameters
       ============================ */
    // Current action
    public static final String PARAM_ACTION = "action";
    // Current page (pagination)
    public static final String PARAM_CURRENT_PAGE = "currentPage";
    // Customer list
    public static final String PRAM_CUSTOMERS = "customers";
    // Disable first page
    public static final String PRAM_DISABLE_FIRST = "disableFirst";
    // Disable previous page
    public static final String PRAM_DISABLE_PREV = "disablePrevious";
    // Disable next page
    public static final String PRAM_DISABLE_NEXT = "disableNext";
    // Disable last page
    public static final String PRAM_DISABLE_LAST = "disableLast";

    /* ============================
       Error message keys
       ============================ */
    // Login failed
    public static final String ERROR_MSG_LOGIN_FAILED = "error.login.failed";
    // Customer ID required
    public static final String ERROR_CUSTOMER_ID_REQUIRED = "error.customerId.required";
    // User ID required
    public static final String ERROR_MSG_USER_ID_REQUIRED = "error.userId.required";
    // Password required
    public static final String ERROR_MSG_PASSWORD_REQUIRED = "error.password.required";
    // Invalid birthday range
    public static final String ERROR_MSG_BIRTHDAY_RANGE = "error.birthday.range";
    // Invalid birthdayFrom format
    public static final String ERROR_MSG_BIRTHDAY_FROM_FORMAT = " error.birthdayFrom.format";
    // Invalid birthdayTo format
    public static final String ERROR_MSG_BIRTHDAY_TO_FORMAT = " error.birthdayTo.format";
    // Invalid birthday
    public static final String ERROR_MSG_BIRTHDAY_INVALID = "error.birthday.invalid";
    // Invalid email
    public static final String ERROR_MSG_EMAIL_INVALID = "error.email.invalid";

    /* ============================
       Other params
       ============================ */
    // Total count
    public static final String PARAM_TOTAL_COUNT = "totalCound";
    // User ID
    public static final String PARAM_USER_ID = "userId";
    // Password
    public static final String PARAM_PASSWORD = "password";
    // Current day
    public static final String PARAM_CURRENT_DAY = "currentDay";
    // Customer IDs
    public static final String PARAM_CUSTOMER_IDS = "customerIDs";
}
