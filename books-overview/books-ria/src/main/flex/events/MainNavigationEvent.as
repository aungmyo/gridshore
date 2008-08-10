package events {
    import flash.events.Event;

    public class MainNavigationEvent extends Event {
        public static var SELECT_ITEM:String = 'selectitem';

        public var clickedItem:String;

        public function MainNavigationEvent(eventType:String,clickedItem:String) {
            super(eventType, false, false);
            this.clickedItem = clickedItem;
        }
    }
}