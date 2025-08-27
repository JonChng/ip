package jeff.storage;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

class StorageTest {
    
    void testLoad() {
        
        Storage storage = new Storage();
        ArrayList<String> result = storage.load();
        assert result != null;
    }
    
    void testSaveAndLoad() {

        Storage storage = new Storage();
        ArrayList<String> testData = new ArrayList<>();
        testData.add("T|0|Test todo");
        
        storage.save(testData);
        
        ArrayList<String> loadedData = storage.load();
        assert loadedData.contains("T|0|Test todo");
    }
    
    void testSaveEmpty() {

        Storage storage = new Storage();
        ArrayList<String> emptyData = new ArrayList<>();
        storage.save(emptyData);
        
        ArrayList<String> loadedData = storage.load();
        assert loadedData.size() == 0;
    }
}
