package bookingbugAPI2.models.params;


public class QuestionListParams extends Params<QuestionListParams> {

    String detail_group_id;

    public String getDetail_group_id() {
        return detail_group_id;
    }

    public QuestionListParams setDetail_group_id(String detail_group_id) {
        this.detail_group_id = detail_group_id;
        return this;
    }
}
