package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;

public class Answer extends BBRoot{

    public Answer(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public Answer(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Answer() {
    }

    /**
     * Returns the  answer's id.
     *
     * @return The id associated with the current Answer object.
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the answer's value.
     *
     * @return The value associated with the current Answer object.
     */
    public String getValue() {
        return get("value");
    }

    /**
     * Returns the answer's price.
     *
     * @return The price associated with the current Answer object.
     */
    public Double getPrice() {
        return getDouble("price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the question id.
     *
     * @return The question id associated with the current Answer object.
     */
    public Integer getQuestionId() {
        return getInteger("question_id", INTEGER_DEFAULT_VALUE);
    }


    /**
     * Returns the  admin only attribute.
     *
     * @return The admin only attribute associated with the current Answer object.
     */
    public Boolean getAdminOnly() {
        return getBoolean("admin_only", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the  important attribute.
     *
     * @return The important attribute associated with the current Answer object.
     */
    public Boolean getImportant() {
        return getBoolean("important", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the  question.
     *
     * @return The question associated with the current Answer object.
     */
    public Question getQuestion() {
        return new Question(new HttpServiceResponse(getResource("question")));
    }

    /**
     * Returns the  question text .
     *
     * @return The question text associated with the current Answer object.
     */
    public String getQuestionText() {
        return get("question_text");
    }

    /**
     * Returns the outcome.
     *
     * @return The outcome associated with the current Answer object.
     */
    public Boolean getOutcome() {
        return getBoolean("outcome", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the  company id.
     *
     * @return The company id associated with the current Answer object.
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the  question link .
     *
     * @return The question link associated with the current Answer object.
     */
    public String getQuestionLink() {
        return getLink("question");
    }
}
