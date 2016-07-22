package bookingbugAPI.models.params;


public class ScheduleParams {
    public static class Create extends Params<Create> {
        String name;
        String desc;
        Integer style;

        public String getName() {
            return name;
        }

        public Create setName(String name) {
            this.name = name;
            return this;
        }

        public String getDesc() {
            return desc;
        }

        public Create setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Integer getStyle() {
            return style;
        }

        public Create setStyle(Integer style) {
            this.style = style;
            return this;
        }
    }

    public static class Update extends Params<Update> {

        String name;
        String desc;
        Integer style;

        public String getName() {
            return name;
        }

        public Update setName(String name) {
            this.name = name;
            return this;
        }

        public String getDesc() {
            return desc;
        }

        public Update setDesc(String desc) {
            this.desc = desc;
            return this;
        }

        public Integer getStyle() {
            return style;
        }

        public Update setStyle(Integer style) {
            this.style = style;
            return this;
        }
    }
}
