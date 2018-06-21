package info.logconsole.admin.event.events;
/** 
 * @author xiahongjian
 * @time   2018-06-21 00:44:26
 */
public class GenericEvent<T> {
        protected T payload;
        protected String eventType;

        public GenericEvent() {}

        public GenericEvent(T payload) {
            this.payload = payload;
        }

        public GenericEvent(T payload, String eventType) {
            this.payload = payload;
            this.eventType = eventType;
        }

        public T getPayload() {
            return payload;
        }

        public void setPayload(T payload) {
            this.payload = payload;
        }

        public String getEventType() {
            return eventType;
        }

        public void setEventType(String eventType) {
            this.eventType = eventType;
        }
}
