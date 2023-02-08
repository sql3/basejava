import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        size++;
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
        size--;
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
            size++;
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
        return size;
    }
}
