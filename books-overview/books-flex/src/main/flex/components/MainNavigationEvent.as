package components {
    import mx.controls.Label;
    import mx.core.UIComponent;
    import flash.events.Event;
    public class MainNavigationEvent extends Event {
        public static var SELECT_ITEM:String = 'selectitem';

        public var clickedItem:String;

        public function createRightUIComponent():UIComponent {
            var createdComponent:UIComponent;
            switch (clickedItem) {
                case 'allbooks' :
                    createdComponent = new FilteredBooks();
                    break;
                case 'newbook' :
                    createdComponent = new BookForm();
                    break;
                default:
                    var label:Label = new Label();
                    label.text = clickedItem;
                    createdComponent = label;
            }
            return createdComponent;
        }

        public function MainNavigationEvent(eventType:String,clickedItem:String) {
            super(eventType, false, false);
            this.clickedItem = clickedItem;
        }
    }
}