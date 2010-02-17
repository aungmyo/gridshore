package managers {
import mx.collections.ArrayCollection;

public class AuthorsManager {
    [Bindable] public var authors:ArrayCollection = new ArrayCollection();

    public function AuthorsManager() {
    }

    public function storeAuthors(authors:ArrayCollection):void {
        this.authors = authors; 
    }
}
}