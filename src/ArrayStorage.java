import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        boolean nullInStorage = false;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                nullInStorage = true;
                break;
            }
        }
        if (!nullInStorage) {
            System.err.println("The storage is full");
        }
    }

    Resume get(String uuid) {
        for (Resume i : storage) {
            if (i == null) {
                break;
            }
            if (i.uuid.equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    void delete(String uuid) {
        int box = -1;
        int firstNullIndex = storage.length;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                firstNullIndex = i;
                break;
            } else if (storage[i].uuid.equals(uuid)) {
                box = i;
                break;
            }
        }
        if (box == -1) {
            System.err.println("No resume was found");
        }
        Resume[] copy = Arrays.copyOfRange(storage, box + 1, firstNullIndex);
        for (int i = box, j = 0; i < copy.length; i++, j++) {
            if (storage[i] == null) {
                break;
            }
            storage[i] = copy[j];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int box = storage.length;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                box = i;
                break;
            }
        }
        return Arrays.copyOfRange(storage, 0, box);
    }

    int size() {
        int size = 0;
        for (Resume i : storage) {
            if (i == null) {
                return size;
            }
            size++;
        }
        return size;
    }
}
